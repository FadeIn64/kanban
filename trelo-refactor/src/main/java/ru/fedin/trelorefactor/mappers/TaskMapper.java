package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.eintites.Task;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DataMapper<Task, TaskDto>{

    @Mapping(
            target = "desk.id",
            source = "dto.deskId"
    )
    @Mapping(
            target = "column.id",
            source = "dto.columnId"
    )
    @Override
    Task toEntity(TaskDto dto);
    @Mapping(
            target = "deskId",
            source = "entity.desk.id"
    )
    @Mapping(
            target = "columnId",
            source = "entity.column.id"
    )
    @Override
    TaskDto toDto(Task entity);

    @Mapping(
            target = "desk.id",
            source = "dtos.deskId"
    )
    @Mapping(
            target = "column.id",
            source = "dtos.columnId"
    )
    @Override
    Collection<Task> toEntity(Collection<TaskDto> dtos);

    @Mapping(
            target = "deskId",
            source = "entities.desk.id"
    )
    @Mapping(
            target = "columnId",
            source = "entities.column.id"
    )
    @Override
    Collection<TaskDto> toDto(Collection<Task> entities);

    @Mapping(
            target = "desk.id",
            source = "dtos.deskId"
    )
    @Mapping(
            target = "column.id",
            source = "dtos.columnId"
    )
    @Override
    List<Task> toEntity(List<TaskDto> dtos);

    @Mapping(
            target = "deskId",
            source = "entities.desk.id"
    )
    @Mapping(
            target = "columnId",
            source = "entities.column.id"
    )
    @Override
    List<TaskDto> toDto(List<Task> entities);
}
