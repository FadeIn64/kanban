package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.eintites.DeskTask;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DataMapper<DeskTask, DeskTaskDTO>{
}
