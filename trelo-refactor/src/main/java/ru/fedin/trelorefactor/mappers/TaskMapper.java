package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.eintites.Task;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DataMapper<Task, TaskDto>{

}
