package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.FileReq;
import ru.fedin.treloclient.dtos.response.FileRes;

@Mapper(componentModel = "spring")
public interface FileMapper extends DataMapper<FileRes, FileReq> {
}
