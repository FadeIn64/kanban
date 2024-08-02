package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

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
