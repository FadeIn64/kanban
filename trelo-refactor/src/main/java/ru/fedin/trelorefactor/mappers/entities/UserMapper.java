package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.User;
import ru.fedin.trelorefactor.mappers.DataMapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends DataMapper<User, UserDto> {
}
