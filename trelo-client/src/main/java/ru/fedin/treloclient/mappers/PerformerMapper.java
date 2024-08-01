package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.TaskPerformerReq;
import ru.fedin.treloclient.dtos.response.TaskPerformerRes;

@Mapper(componentModel = "spring")
public interface PerformerMapper extends DataMapper<TaskPerformerRes, TaskPerformerReq>{
}
