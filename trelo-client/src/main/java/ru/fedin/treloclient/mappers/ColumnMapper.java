package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

@Mapper(componentModel = "spring")
public interface ColumnMapper extends DataMapper<DeskColumnRes, DeskColumnReq> {
}
