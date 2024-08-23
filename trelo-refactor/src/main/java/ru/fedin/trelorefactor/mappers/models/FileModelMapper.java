package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.minio.FileDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.FileModel;

@Mapper(componentModel = "spring")
public interface FileModelMapper extends DataMapper<FileModel, FileDto> {
}
