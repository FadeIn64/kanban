package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.mappers.models.TaskModelMapper;
import ru.fedin.treloclient.messaging.*;
import ru.fedin.treloclient.models.TaskModel;
import ru.fedin.treloclient.services.TaskService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskListener {

    private final TaskService taskService;
    private final TaskModelMapper taskMapper;

    @KafkaListener(topics = "${kafka.topic.task}",
            groupId = "server",
            containerFactory = "taskKafkaListenerContainerFactory")
    public void listener(Message<TaskModel, TaskAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key) {
        log.info("Received Task message: {}", message);


    }

}
