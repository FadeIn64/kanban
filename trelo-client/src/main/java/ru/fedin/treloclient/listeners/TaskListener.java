package ru.fedin.treloclient.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;
import ru.fedin.treloclient.services.TaskService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskListener {


    @KafkaListener(topics = "#{replyTaskTopic}",
            groupId = "client",
            containerFactory = "replyTaskKafkaListenerContainerFactory")
    void listener( DeskTaskRes task){
        log.info("Task: {}", task);

    }

}
