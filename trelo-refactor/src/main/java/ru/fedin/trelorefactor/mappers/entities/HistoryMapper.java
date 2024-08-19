package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.HistoryDto;
import ru.fedin.trelorefactor.eintites.History;
import ru.fedin.trelorefactor.mappers.DataMapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class, ColumnMapper.class})
public interface HistoryMapper extends DataMapper<History, HistoryDto> {
}
