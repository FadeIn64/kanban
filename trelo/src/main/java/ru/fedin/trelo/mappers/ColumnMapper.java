package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskColumnDTO;
import ru.fedin.trelo.eintites.DeskColumn;

@Mapper(componentModel = "spring")
public interface ColumnMapper extends DataMapper<DeskColumn, DeskColumnDTO>{
}
