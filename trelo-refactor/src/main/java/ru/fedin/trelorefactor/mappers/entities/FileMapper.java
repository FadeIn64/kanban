package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.minio.FileDto;
import ru.fedin.trelorefactor.eintites.File;
import ru.fedin.trelorefactor.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends DataMapper<File, FileDto> {
}
