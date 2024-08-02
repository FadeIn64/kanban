package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.cache.DeskCacheService;
import ru.fedin.treloclient.dtos.response.DeskRes;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeskListener {

    private final DeskCacheService cacheService;

    @KafkaListener(topics = "#{replyDeskTopic}",
                    groupId = "desk",
                    containerFactory = "replyDeskKafkaListenerContainerFactory")
    void listener( DeskRes desk){
        log.info("Desk: {}", desk);
        if ("".equals(desk.getName())){
            cacheService.delete(desk.getId());
            return;
        }
        cacheService.save(desk);
    }

}
