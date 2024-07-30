package ru.fedin.trelo.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.trelo.dtos.DeskDTO;

import java.util.UUID;

@Component
@Slf4j
public class DeskListener {
    @KafkaListener(topics = "${kafka.topic.desk}",
                    groupId = "server",
                    containerFactory = "deskKafkaListenerContainerFactory")
    void listener(DeskDTO dto){
        log.info("Desk: {}", dto);
    }

}
