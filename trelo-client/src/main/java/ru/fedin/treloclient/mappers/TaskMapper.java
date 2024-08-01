package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DataMapper<DeskTaskRes, DeskTaskReq>{
}
