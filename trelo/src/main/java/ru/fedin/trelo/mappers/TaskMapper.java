package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.eintites.DeskTask;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ContributorMapper.class, FileMapper.class})
public interface TaskMapper extends DataMapper<DeskTask, DeskTaskDTO>{

    @Mapping(
        target = "desk.id",
        source = "dto.desk"
    )
    @Override
    DeskTask toEntity(DeskTaskDTO dto);

    @Mapping(
            target = "desk",
            source = "entity.desk.id"
    )
    @Override
    DeskTaskDTO toDto(DeskTask entity);

    @Mapping(
            target = "desk.id",
            source = "dto.desk"
    )
    @Override
    Collection<DeskTask> toEntity(Collection<DeskTaskDTO> dto);

    @Mapping(
            target = "desk",
            source = "entities.desk.id"
    )
    @Override
    Collection<DeskTaskDTO> toDto(Collection<DeskTask> entities);

    @Mapping(
            target = "desk.id",
            source = "dto.desk"
    )
    @Override
    List<DeskTask> toEntity(List<DeskTaskDTO> dto);

    @Mapping(
            target = "desk",
            source = "entities.desk.id"
    )
    @Override
    List<DeskTaskDTO> toDto(List<DeskTask> entities);
}
