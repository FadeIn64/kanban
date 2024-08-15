package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.HistoryDto;
import ru.fedin.trelorefactor.eintites.History;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class, ColumnMapper.class})
public interface HistoryMapper extends DataMapper<History, HistoryDto> {
}
