package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.minio.FileDTO;
import ru.fedin.trelo.eintites.TaskFile;

@Mapper(componentModel = "spring")
public interface FileMapper extends DataMapper<TaskFile, FileDTO>{
}
