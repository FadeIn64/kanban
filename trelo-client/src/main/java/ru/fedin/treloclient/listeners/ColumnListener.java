package ru.fedin.treloclient.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColumnListener {




    @KafkaListener(topics = "#{replyColumnTopic}",
            groupId = "client",
            containerFactory = "replyColumnKafkaListenerContainerFactory")
    void listener(DeskColumnRes column){
        log.info("Reply message column: {}", column);
    }

}
