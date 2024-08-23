package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.mappers.models.DeskModelMapper;
import ru.fedin.treloclient.messaging.DeskAction;
import ru.fedin.treloclient.messaging.Message;
import ru.fedin.treloclient.messaging.MessageStatus;
import ru.fedin.treloclient.messaging.Status;
import ru.fedin.treloclient.models.DeskModel;
import ru.fedin.treloclient.services.DeskService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeskListener {

    private final DeskService deskService;
    private final DeskModelMapper deskMapper;
    private final KafkaTemplate<UUID, Message<DeskModel, DeskAction>> deskTemplate;

    @KafkaListener(topics = "${kafka.topic.desk}",
            groupId = "server",
            containerFactory = "deskKafkaListenerContainerFactory")
    public void listener( Message<DeskModel, DeskAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key) {

        if (!message.getStatus().getStatus().name().equals(Status.OK.name())) {
            log.error("Received Desk message with status: {}", message.getStatus());
            return;
        }
        message.getAction().action(message.getMessage(), deskService);
    }

}
