package ru.fedin.trelorefactor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.eintites.ColumnEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnMapper extends DataMapper<ColumnEntity, ColumnDto> {

    @Mapping(
            target = "desk.id",
            source = "dto.deskId"
    )
    @Override
    ColumnEntity toEntity(ColumnDto dto);

    @Mapping(
            target = "deskId",
            source = "entity.desk.id"
    )
    @Override
    ColumnDto toDto(ColumnEntity entity);

    @Mapping(
            target = "desk.id",
            source = "dtos.deskId"
    )
    @Override
    Collection<ColumnEntity> toEntity(Collection<ColumnDto> dtos);

    @Mapping(
            target = "deskId",
            source = "entities.desk.id"
    )
    @Override
    Collection<ColumnDto> toDto(Collection<ColumnEntity> entities);

    @Mapping(
            target = "desk.id",
            source = "dtos.deskId"
    )
    @Override
    List<ColumnEntity> toEntity(List<ColumnDto> dtos);

    @Mapping(
            target = "deskId",
            source = "entities.desk.id"
    )
    @Override
    List<ColumnDto> toDto(List<ColumnEntity> entities);
}
