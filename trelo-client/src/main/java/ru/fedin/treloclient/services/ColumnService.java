package ru.fedin.treloclient.services;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;
import ru.fedin.treloclient.mappers.ColumnMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColumnService {


    private final RestClient restClient;
    private final KafkaTemplate<UUID, DeskColumnReq> columnTemplate;
    private final ColumnMapper mapper;
    @Value("${kafka.topic.column}")
    private String columnTopic;



    public DeskColumnRes findById(Integer id){

        try {
            var res = restClient
                    .get()
                    .uri("/column/" + id)
                    .retrieve()
                    .toEntity(DeskColumnRes.class);

            var entity = res.getBody();

            return entity;
        }
        catch (Exception e){
            return DeskColumnRes.builder().id(0).build();
        }

    }


    public boolean create(DeskColumnReq dto){
        if ("".equals(dto.getName()))
            return false;
        dto.setId(0);
        columnTemplate.send(columnTopic, Generators.timeBasedGenerator().generate(), dto);
        return true;
    }


    public boolean rename(Integer id, String newName){

        if ("".equals(newName))
            return false;

        var column = this.findById(id);
        if (column.getId() == 0)
            return false;

        column.setName(newName);

        columnTemplate.send(columnTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(column));
        return true;
    }


    public boolean move(Integer id, int offset){

        var column = this.findById(id);
        if (column.getId() == 0)
            return false;

        offset = column.getNext() + offset;
        column.setNext(offset);

        columnTemplate.send(columnTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(column));
        return true;

    }



    public boolean removeColumn(Integer id){

        var column = this.findById(id);
        if (column.getId() == 0)
            return false;

        column.setName("");

        columnTemplate.send(columnTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(column));
        return true;
    }
}
