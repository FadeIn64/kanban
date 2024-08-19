package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.eintites.Task;
import ru.fedin.trelorefactor.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DataMapper<Task, TaskDto> {

}
