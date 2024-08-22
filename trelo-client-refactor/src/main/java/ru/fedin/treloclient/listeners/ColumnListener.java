package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.mappers.models.ColumnModelMapper;
import ru.fedin.treloclient.messaging.ColumnAction;
import ru.fedin.treloclient.messaging.Message;
import ru.fedin.treloclient.messaging.MessageStatus;
import ru.fedin.treloclient.messaging.Status;
import ru.fedin.treloclient.models.ColumnModel;
import ru.fedin.treloclient.services.ColumnService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColumnListener {
    private final ColumnService columnService;
    private final ColumnModelMapper columnMapper;

    @KafkaListener(topics = "${kafka.topic.column}",
            groupId = "server",
            containerFactory = "columnKafkaListenerContainerFactory")
    public void listener( Message<ColumnModel, ColumnAction> message, @Header(KafkaHeaders.RECEIVED_KEY) UUID key){
        log.info("Received Column message: {}", message);


    }

}
