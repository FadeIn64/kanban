package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.TaskHistoryDTO;
import ru.fedin.trelo.dtos.kafka.TaskHistoryRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface TaskHistoryMapperKafka extends DataMapper<TaskHistoryRes, TaskHistoryDTO> {
}
