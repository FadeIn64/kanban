package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskDTO;
import ru.fedin.trelo.dtos.kafka.DeskRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface DeskMapperKafka extends DataMapper<DeskRes, DeskDTO> {
}
