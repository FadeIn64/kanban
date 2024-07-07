package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskDTO;
import ru.fedin.trelo.eintites.Desk;

@Mapper(componentModel = "spring")
public interface DeskMapper extends DataMapper<Desk, DeskDTO>{
}
