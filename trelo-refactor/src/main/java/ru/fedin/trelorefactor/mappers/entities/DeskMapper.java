package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.eintites.Desk;
import ru.fedin.trelorefactor.mappers.DataMapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class, ColumnMapper.class})
public interface DeskMapper extends DataMapper<Desk, DeskDto> {
}
