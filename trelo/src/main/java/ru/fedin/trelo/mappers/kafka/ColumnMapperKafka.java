package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskColumnDTO;
import ru.fedin.trelo.dtos.kafka.DeskColumnRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface ColumnMapperKafka extends DataMapper<DeskColumnRes, DeskColumnDTO> {
}
