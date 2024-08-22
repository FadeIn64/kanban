package ru.fedin.trelorefactor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.models.TaskModelMapper;
import ru.fedin.trelorefactor.messaging.*;
import ru.fedin.trelorefactor.models.TaskModel;
import ru.fedin.trelorefactor.services.TaskService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskListener {

    private final TaskService taskService;
    private final TaskModelMapper taskMapper;
    private final KafkaTemplate<UUID, Message<TaskModel, TaskAction>> taskTemplate;

    @KafkaListener(topics = "${kafka.topic.task}",
            groupId = "server",
            containerFactory = "taskKafkaListenerContainerFactory")
    public void listener( Message<TaskModel, TaskAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key){
        log.info("Received Task message: {}", message);


        Message<TaskModel, TaskAction> reply = new Message<>();
        if (message.getAction().name().equals(TaskAction.REMOVE.name())){
            reply.setAction(TaskAction.REMOVE);
        }
        else {
            reply.setAction(TaskAction.CACHE);
        }

        if (message.getMessage().getId() == null){
            reply.setStatus(new MessageStatus(Status.ERROR, "id equals null"));
            reply.setMessage(message.getMessage());
            send(reply, key);
            return;
        }

        try {
            reply.setMessage(taskMapper.toEntity(message.getAction().action(taskMapper.toDto(message.getMessage()), taskService)));
            reply.setStatus(new MessageStatus(Status.OK, "Ok"));
        }
        catch (ModifyDataException e){
            log.error("ModifyDataException", e);
            reply.setMessage(message.getMessage());
            reply.setStatus(new MessageStatus(Status.BAD_REQUEST, "ModifyDataException: " + e.getMessage()));
        }
        catch (Exception e){
            log.error("Exception", e);
            reply.setMessage(message.getMessage());
            reply.setStatus(new MessageStatus(Status.ERROR, e.getClass().getName() + ": " + e.getMessage()));
        }
        send(reply, key);
    }

    private void send(Message<TaskModel, TaskAction> message, UUID key){
        taskTemplate.sendDefault(key, message);
        log.info("Reply Task message: {}", message);
    }

}
