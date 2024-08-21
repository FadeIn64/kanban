package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.HistoryDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.HistoryModel;


@Mapper(componentModel = "spring", uses = {UserModelMapper.class, TaskModelMapper.class, ColumnModelMapper.class})
public interface HistoryModelMapper extends DataMapper<HistoryModel, HistoryDto> {
}
