package ru.fedin.trelorefactor.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.messaging.DeskAction;
import ru.fedin.trelorefactor.messaging.Message;
import ru.fedin.trelorefactor.messaging.MessageStatus;
import ru.fedin.trelorefactor.messaging.Status;
import ru.fedin.trelorefactor.services.DeskService;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeskListener {

    private final DeskService deskService;

    @KafkaListener(topics = "${kafka.topic.desk}",
            groupId = "server",
            containerFactory = "deskKafkaListenerContainerFactory")
    void listener( Message<DeskDto, DeskAction> message){
        log.info("Received Desk message: {}", message);

        Message<DeskDto, DeskAction> receive = new Message<>();
        receive.setAction(DeskAction.CACHE);

        try {
            receive.setMessage(message.getAction().action(message.getMessage(), deskService));
            receive.setStatus(new MessageStatus(Status.OK, "Ok"));
        }
        catch (ModifyDataException e){
            log.error("ModifyDataException", e);
            receive.setMessage(message.getMessage());
            receive.setStatus(new MessageStatus(Status.BAD_REQUEST, "ModifyDataException: " + e.getMessage()));
        }
        catch (Exception e){
            log.error("Exception", e);
            receive.setStatus(new MessageStatus(Status.ERROR, e.getClass().getName() + ": " + e.getMessage()));
        }

        log.info("Received Desk received: {}", receive);

    }

}
