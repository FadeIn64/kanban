package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.ColumnDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.ColumnModel;


@Mapper(componentModel = "spring")
public interface ColumnModelMapper extends DataMapper<ColumnModel, ColumnDto> {

}
