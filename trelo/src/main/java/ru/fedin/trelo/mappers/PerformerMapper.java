package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.TaskPerformerDTO;
import ru.fedin.trelo.eintites.TaskPerformer;

@Mapper(componentModel = "spring")
public interface PerformerMapper extends DataMapper<TaskPerformer, TaskPerformerDTO>{
}
