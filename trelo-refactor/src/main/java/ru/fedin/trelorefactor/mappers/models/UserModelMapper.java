package ru.fedin.trelorefactor.mappers.models;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.mappers.DataMapper;
import ru.fedin.trelorefactor.models.UserModel;

@Mapper(componentModel = "spring")
public interface UserModelMapper extends DataMapper<UserModel, UserDto> {
}
