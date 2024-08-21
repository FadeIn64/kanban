package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.TaskDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.TaskModel;


@Mapper(componentModel = "spring")
public interface TaskModelMapper extends DataMapper<TaskModel, TaskDto> {

}
