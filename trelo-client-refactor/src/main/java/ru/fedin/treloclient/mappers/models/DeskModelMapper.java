package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.DeskDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.DeskModel;


@Mapper(componentModel = "spring", uses = {UserModelMapper.class, TaskModelMapper.class, ColumnModelMapper.class})
public interface DeskModelMapper extends DataMapper<DeskModel, DeskDto> {
}
