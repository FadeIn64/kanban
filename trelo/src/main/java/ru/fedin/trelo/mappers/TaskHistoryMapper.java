package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.TaskHistoryDTO;
import ru.fedin.trelo.eintites.TaskHistory;

@Mapper(componentModel = "spring", uses = {ColumnMapper.class})
public interface TaskHistoryMapper extends DataMapper<TaskHistory, TaskHistoryDTO>{
}
