package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.dtos.kafka.DeskContributorRes;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface ContributorMapperKafka extends DataMapper<DeskContributorRes, DeskContributorDTO> {
}
