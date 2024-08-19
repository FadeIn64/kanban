package ru.fedin.trelorefactor.mappers.entities;

import org.mapstruct.Mapper;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.eintites.ColumnEntity;
import ru.fedin.trelorefactor.mappers.DataMapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnMapper extends DataMapper<ColumnEntity, ColumnDto> {


    @Override
    ColumnEntity toEntity(ColumnDto dto);


    @Override
    ColumnDto toDto(ColumnEntity entity);


    @Override
    Collection<ColumnEntity> toEntity(Collection<ColumnDto> dtos);


    @Override
    Collection<ColumnDto> toDto(Collection<ColumnEntity> entities);


    @Override
    List<ColumnEntity> toEntity(List<ColumnDto> dtos);


    @Override
    List<ColumnDto> toDto(List<ColumnEntity> entities);
}
