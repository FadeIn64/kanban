package ru.fedin.treloclient.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskListener {


    @KafkaListener(topics = "#{replyTaskTopic}",
            groupId = "client",
            containerFactory = "replyTaskKafkaListenerContainerFactory")
    void listener( DeskTaskRes task){
        log.info("Task: {}", task);

    }

}
