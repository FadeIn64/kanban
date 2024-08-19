package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.ColumnModel;

@Mapper(componentModel = "spring")
public interface ColumnModelMapper extends DataMapper<ColumnModel, ColumnDto> {

}
