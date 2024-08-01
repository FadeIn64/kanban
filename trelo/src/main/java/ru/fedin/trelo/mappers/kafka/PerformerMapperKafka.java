package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.TaskPerformerDTO;
import ru.fedin.trelo.dtos.kafka.TaskPerformerRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface PerformerMapperKafka extends DataMapper<TaskPerformerRes, TaskPerformerDTO> {
}
