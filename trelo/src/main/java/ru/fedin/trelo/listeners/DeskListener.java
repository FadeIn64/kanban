package ru.fedin.trelo.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.trelo.dtos.DeskDTO;
import ru.fedin.trelo.services.DeskService;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeskListener {

    private final DeskService deskService;

    @KafkaListener(topics = "${kafka.topic.desk}",
                    groupId = "server",
                    containerFactory = "deskKafkaListenerContainerFactory")
    void listener(DeskDTO dto){
        if (dto.getId() == null){
            dto = deskService.create(dto);
        }
    }

}
