package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.TaskHistoryReq;
import ru.fedin.treloclient.dtos.response.TaskHistoryRes;

@Mapper(componentModel = "spring")
public interface TaskHistoryMapper extends DataMapper<TaskHistoryRes, TaskHistoryReq>{
}
