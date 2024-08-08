package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelo.dtos.DeskColumnDTO;
import ru.fedin.trelo.eintites.DeskColumn;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnMapper extends DataMapper<DeskColumn, DeskColumnDTO>{
    @Override
    @Mapping(
            target = "desk.id",
            source = "dto.desk"
    )
    DeskColumn toEntity(DeskColumnDTO dto);

    @Mapping(
            target = "desk",
            source = "entity.desk.id"
    )
    @Override
    DeskColumnDTO toDto(DeskColumn entity);

    @Mapping(
            target = "desk.id",
            source = "dto.desk"
    )
    @Override
    Collection<DeskColumn> toEntity(Collection<DeskColumnDTO> dto);

    @Mapping(
            target = "desk",
            source = "entities.desk.id"
    )
    @Override
    Collection<DeskColumnDTO> toDto(Collection<DeskColumn> entities);

    @Mapping(
            target = "desk.id",
            source = "dto.desk"
    )
    @Override
    List<DeskColumn> toEntity(List<DeskColumnDTO> dto);

    @Mapping(
            target = "desk",
            source = "entities.desk.id"
    )
    @Override
    List<DeskColumnDTO> toDto(List<DeskColumn> entities);
}
