package ru.fedin.trelo.mappers.kafka;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.kafka.FileRes;
import ru.fedin.trelo.dtos.minio.FileDTO;
import ru.fedin.trelo.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface FileMapperKafka extends DataMapper<FileRes, FileDTO> {
}
