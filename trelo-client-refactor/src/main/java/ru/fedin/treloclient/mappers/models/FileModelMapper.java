package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.FileDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.FileModel;

@Mapper(componentModel = "spring")
public interface FileModelMapper extends DataMapper<FileModel, FileDto> {
}
