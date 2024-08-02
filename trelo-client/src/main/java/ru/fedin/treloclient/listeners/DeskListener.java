package ru.fedin.treloclient.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskRes;


import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeskListener {

    @KafkaListener(topics = "#{replyDeskTopic}",
                    groupId = "desk",
                    containerFactory = "replyDeskKafkaListenerContainerFactory")
    void listener( DeskRes desk){
        log.info("Desk: {}", desk);
    }

}
