package ru.fedin.trelorefactor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.models.ColumnModelMapper;
import ru.fedin.trelorefactor.messaging.*;
import ru.fedin.trelorefactor.models.ColumnModel;
import ru.fedin.trelorefactor.services.ColumnService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColumnListener {
    private final ColumnService columnService;
    private final ColumnModelMapper columnMapper;

    @KafkaListener(topics = "${kafka.topic.column}",
            groupId = "server",
            containerFactory = "deskKafkaListenerContainerFactory")
    public void listener( Message<ColumnModel, ColumnAction> message){
        log.info("Received Column message: {}", message);


        Message<ColumnModel, ColumnAction> reply = new Message<>();
        if (message.getAction().name().equals(ColumnAction.REMOVE.name())){
            reply.setAction(ColumnAction.REMOVE);
        }
        else {
            reply.setAction(ColumnAction.CACHE);
        }

        if (message.getMessage().getId() == null || 0L == message.getMessage().getId()){
            reply.setStatus(new MessageStatus(Status.ERROR, "id equals null"));
            reply.setMessage(message.getMessage());
            return;
        }

        try {
            reply.setMessage(columnMapper.toEntity(message.getAction().action(columnMapper.toDto(message.getMessage()), columnService)));
            reply.setStatus(new MessageStatus(Status.OK, "Ok"));
        }
        catch (ModifyDataException e){
            log.error("ModifyDataException", e);
            reply.setMessage(message.getMessage());
            reply.setStatus(new MessageStatus(Status.BAD_REQUEST, "ModifyDataException: " + e.getMessage()));
        }
        catch (Exception e){
            log.error("Exception", e);
            reply.setStatus(new MessageStatus(Status.ERROR, e.getClass().getName() + ": " + e.getMessage()));
        }

        log.info("Reply Column received: {}", reply);

    }
}
