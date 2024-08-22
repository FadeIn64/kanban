package ru.fedin.trelorefactor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.entities.DeskMapper;
import ru.fedin.trelorefactor.mappers.models.DeskModelMapper;
import ru.fedin.trelorefactor.messaging.DeskAction;
import ru.fedin.trelorefactor.messaging.Message;
import ru.fedin.trelorefactor.messaging.MessageStatus;
import ru.fedin.trelorefactor.messaging.Status;
import ru.fedin.trelorefactor.models.DeskModel;
import ru.fedin.trelorefactor.services.DeskService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeskListener {

    private final DeskService deskService;
    private final DeskModelMapper deskMapper;

    @KafkaListener(topics = "${kafka.topic.desk}",
            groupId = "server",
            containerFactory = "deskKafkaListenerContainerFactory")
    public void listener( Message<DeskModel, DeskAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key){
        log.info("Received Desk message: {}", message);


        Message<DeskModel, DeskAction> reply = new Message<>();
        if (message.getAction().name().equals(DeskAction.REMOVE.name())){
            reply.setAction(DeskAction.REMOVE);
        }
        else {
            reply.setAction(DeskAction.CACHE);
        }

        if (message.getMessage().getId() == null){
            reply.setStatus(new MessageStatus(Status.ERROR, "id equals null"));
            reply.setMessage(message.getMessage());
            return;
        }

        try {
            reply.setMessage(deskMapper.toEntity(message.getAction().action(deskMapper.toDto(message.getMessage()), deskService)));
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

        log.info("Reply Desk received: {}", reply);

    }

}
