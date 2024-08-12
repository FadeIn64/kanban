package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends DataMapper<User, UserDto>{
}
