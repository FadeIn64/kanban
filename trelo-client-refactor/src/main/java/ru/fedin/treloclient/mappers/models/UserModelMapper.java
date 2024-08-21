package ru.fedin.treloclient.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.UserDto;
import ru.fedin.treloclient.mappers.DataMapper;
import ru.fedin.treloclient.models.UserModel;


@Mapper(componentModel = "spring")
public interface UserModelMapper extends DataMapper<UserModel, UserDto> {
}
