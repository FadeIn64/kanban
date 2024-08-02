package ru.fedin.trelo.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.dtos.kafka.DeskContributorRes;
import ru.fedin.trelo.dtos.kafka.DeskTaskRes;
import ru.fedin.trelo.mappers.kafka.TaskMapperKafka;
import ru.fedin.trelo.services.TaskService;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskListener {

    private final TaskService taskService;
    private final TaskMapperKafka taskMapper;
    private final KafkaTemplate<UUID, DeskTaskRes> replyTaskTemplate;
    private final String replyTaskTopic;

    @KafkaListener(topics = "${kafka.topic.task}",
            groupId = "server",
            containerFactory = "taskKafkaListenerContainerFactory")
    void listener( DeskTaskRes task){
        log.info("Task: {}", task);
        if (task.getId() == 0){
            task = taskMapper.toEntity(taskService.create(taskMapper.toDto(task)));
            send(task);
            return;
        }

        if (task.getHeader().equals("")){
            taskService.removeTask(task.getId());
            send(task);
            return;
        }

        var dto = taskService.findById(task.getId());

        if (dto.getId() == 0)
            return;

        if (!(task.getColumn() == null) && !dto.getColumn().equals(task.getColumn())){
            if (taskService.changeColumn(task.getId(), task.getColumn())){
                send(task);
            }
            return;
        }

        var newContrs = task.getPerformers().stream()
                .map(p -> p.getContributor().getContributor())
                .toList();
        var oldContrs = dto.getPerformers().stream()
                .map(p -> p.getContributor().getContributor())
                .toList();

        //Добавляем новых участников
        newContrs.stream()
                .filter(perf -> !oldContrs.contains(perf))
                .forEach(perf -> taskService.addPerformer(dto.getId(), perf));

        //Удаляем старых участников
        oldContrs.stream()
                .filter(perf -> !newContrs.contains(perf))
                .forEach(perf -> taskService.removePerformer(dto.getId(), perf));

        if (!dto.equals(taskMapper.toDto(task))){
            task = taskMapper.toEntity(taskService.change(taskMapper.toDto(task)));
        }
        else {
            //Без этого изменения в участниках могут не зафиксироваться в кэше
            task = taskMapper.toEntity(taskService.findById(task.getId()));
        }


        send(task);
    }

    private void send(DeskTaskRes task){
        replyTaskTemplate.send(replyTaskTopic, Generators.timeBasedGenerator().generate(), task);
    }

}
