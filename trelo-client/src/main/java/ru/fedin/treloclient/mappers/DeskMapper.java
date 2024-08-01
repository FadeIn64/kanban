package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.DeskReq;
import ru.fedin.treloclient.dtos.response.DeskRes;

@Mapper(componentModel = "spring")
public interface DeskMapper extends DataMapper<DeskRes, DeskReq> {
}
