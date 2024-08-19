package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.TaskModel;

@Mapper(componentModel = "spring")
public interface TaskModelMapper extends DataMapper<TaskModel, TaskDto> {

}
