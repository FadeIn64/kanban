package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.cache.ColumnCacheService;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColumnListener {

    private final ColumnCacheService cacheService;


    @KafkaListener(topics = "#{replyColumnTopic}",
            groupId = "client",
            containerFactory = "replyColumnKafkaListenerContainerFactory")
    void listener(DeskColumnRes column){
        log.info("Reply message column: {}", column);
        if (column.getId() == null)
            return;
        if ("".equals(column.getName())){
            cacheService.delete(column.getId());
            return;
        }
        cacheService.save(column);
    }

}
