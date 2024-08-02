package ru.fedin.trelo.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.trelo.dtos.kafka.DeskColumnRes;
import ru.fedin.trelo.dtos.kafka.DeskRes;
import ru.fedin.trelo.mappers.kafka.ColumnMapperKafka;
import ru.fedin.trelo.services.ColumnService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ColumnListener {

    private final ColumnService columnService;
    private final ColumnMapperKafka columnMapper;
    private final KafkaTemplate<UUID, DeskColumnRes> replyColumnTemplate;
    private final String replyColumnTopic;


    @KafkaListener(topics = "${kafka.topic.column}",
            groupId = "server",
            containerFactory = "columnKafkaListenerContainerFactory")
    void listener(DeskColumnRes column){

        if(column.getId() == 0){
            column = columnMapper.toEntity(columnService.create(columnMapper.toDto(column)));
            send(column);
            return;
        }

        if ("".equals(column.getName())){
            columnService.removeColumn(column.getId());
            send(column);
            return;
        }

        var dto = columnService.findById(column.getId());

        if (dto.getId() == 0)
            return;

        if (!dto.getName().equals(column.getName())){
            column = columnMapper.toEntity(columnService.rename(column.getId(), column.getName()));
            send(column);
            return;
        }

        var offset = column.getNext() - dto.getNext();
        if (offset == 0)
            return;

        var columns = columnService.move(column.getId(), offset);
        column = columnMapper.toEntity(
                columns.stream()
                        .filter(c -> c.getId().equals(dto.getId()))
                        .findFirst().orElse(dto)
                );
        send(column);

    }

    private void send(DeskColumnRes column){
        replyColumnTemplate.send(replyColumnTopic, Generators.timeBasedGenerator().generate(), column);
    }
}
