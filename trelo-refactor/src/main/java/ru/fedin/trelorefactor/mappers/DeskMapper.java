package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.eintites.Desk;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class, ColumnMapper.class})
public interface DeskMapper extends DataMapper<Desk, DeskDto>{
}
