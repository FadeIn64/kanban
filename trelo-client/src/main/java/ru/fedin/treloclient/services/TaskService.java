package ru.fedin.treloclient.services;

import com.fasterxml.uuid.Generators;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.cache.TaskCacheService;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.requests.TaskPerformerReq;
import ru.fedin.treloclient.dtos.response.DeskContributorRes;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;
import ru.fedin.treloclient.dtos.response.TaskPerformerRes;
import ru.fedin.treloclient.mappers.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RestClient restClient;
    private final TaskMapper mapper;
    private final KafkaTemplate<UUID, DeskTaskReq> taskTemplate;
    @Value("${kafka.topic.task}")
    private String taskTopic;
    private final TaskCacheService cacheService;



    public DeskTaskRes findById(int id){
        var opt = cacheService.findById(id);
        if (opt.isPresent())
            return opt.get();

        try {
            var res = restClient.
                    get()
                    .uri("/task/"+id)
                    .retrieve()
                    .toEntity(DeskTaskRes.class);

            var entity = res.getBody();
            cacheService.save(entity);
            return entity;
        }catch (Exception e){
            return DeskTaskRes.builder().id(0).build();
        }


    }


    public boolean create(DeskTaskReq dto){
        if ("".equals(dto.getHeader()) || "".equals(dto.getAuthor()))
             return false;

        dto.setId(0);
        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), dto);
        return true;
    }


    public boolean changeColumn(Integer taskId, Integer newColumn){

        var task = this.findById(taskId);
        if (task.getId() == 0)
            return false;

        task.setColumn(newColumn);

        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(task));
        return true;
    }


    public boolean change(DeskTaskReq dto){

        if ("".equals(dto.getHeader()) || "".equals(dto.getAuthor()))
            return false;

        var task = this.findById(dto.getId());
        if (task.getId() == 0)
            return false;


        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), dto);
        return true;
    }


    public boolean addPerformer(Integer taskId, String newContributor){
        var task = this.findById(taskId);
        if (task.getId() == 0)
            return false;

        System.out.println(task.getDesk());

       var contr = DeskContributorRes.builder().contributor(newContributor).desk(task.getDesk()).build();
       var performer = TaskPerformerRes.builder().contributor(contr).task(taskId).build();
       task.getPerformers().add(performer);

        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(task));
        return true;
    }

    public boolean removePerformer(Integer taskId, @NotNull String oldContributor){
        var task = this.findById(taskId);
        if (task.getId() == 0)
            return false;

        task.setPerformers(task.getPerformers().stream()
                .filter(p -> !p.getContributor().getContributor().equals(oldContributor))
                .toList());

        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(task));
        return true;
    }

    public boolean removeTask(Integer id){
        var task = this.findById(id);
        if (task.getId() == 0)
            return false;

        task.setHeader("");

        taskTemplate.send(taskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(task));
        return true;
    }

}
