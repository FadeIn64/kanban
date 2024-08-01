package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.dtos.kafka.DeskTaskRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface TaskMapperKafka extends DataMapper<DeskTaskRes, DeskTaskDTO> {
}
