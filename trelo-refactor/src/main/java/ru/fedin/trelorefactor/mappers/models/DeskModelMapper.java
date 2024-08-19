package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.DeskModel;

@Mapper(componentModel = "spring", uses = {UserModelMapper.class, TaskModelMapper.class, ColumnModelMapper.class})
public interface DeskModelMapper extends DataMapper<DeskModel, DeskDto> {
}
