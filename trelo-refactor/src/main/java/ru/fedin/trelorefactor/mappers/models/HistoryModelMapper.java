package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.HistoryDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.HistoryModel;

@Mapper(componentModel = "spring", uses = {UserModelMapper.class, TaskModelMapper.class, ColumnModelMapper.class})
public interface HistoryModelMapper extends DataMapper<HistoryModel, HistoryDto> {
}
