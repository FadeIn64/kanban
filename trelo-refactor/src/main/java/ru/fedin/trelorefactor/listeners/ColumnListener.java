package ru.fedin.trelorefactor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.models.ColumnModelMapper;
import ru.fedin.trelorefactor.messaging.*;
import ru.fedin.trelorefactor.models.ColumnModel;
import ru.fedin.trelorefactor.services.ColumnService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColumnListener {
    private final ColumnService columnService;
    private final ColumnModelMapper columnMapper;
    private final KafkaTemplate<UUID, Message<ColumnModel, ColumnAction>> columnTemplate;

    @KafkaListener(topics = "${kafka.topic.column}",
            groupId = "server",
            containerFactory = "columnKafkaListenerContainerFactory")
    public void listener( Message<ColumnModel, ColumnAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key){
        log.info("Received Column message: {}", message);


        Message<ColumnModel, ColumnAction> reply = new Message<>();
        if (message.getAction().name().equals(ColumnAction.REMOVE.name())){
            reply.setAction(ColumnAction.REMOVE);
        }
        else {
            reply.setAction(ColumnAction.CACHE);
        }

        if (message.getMessage().getId() == null){
            reply.setStatus(new MessageStatus(Status.ERROR, "id equals null"));
            reply.setMessage(message.getMessage());
            send(reply, key);
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
            reply.setMessage(message.getMessage());
            reply.setStatus(new MessageStatus(Status.ERROR, e.getClass().getName() + ": " + e.getMessage()));
        }

        send(reply, key);

    }

    private void send(Message<ColumnModel, ColumnAction> message, UUID key){
        log.info("Reply Column received: {}", message);
        columnTemplate.sendDefault(key, message);
    }
}
