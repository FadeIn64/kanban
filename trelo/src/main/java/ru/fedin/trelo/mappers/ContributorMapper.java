package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.eintites.DeskContributor;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContributorMapper extends DataMapper<DeskContributor, DeskContributorDTO>{

    @Mapping(
        target = "desk.id",
        source = "dto.desk"
    )
    @Override
    DeskContributor toEntity(DeskContributorDTO dto);

    @Mapping(
        target = "desk",
        source = "entity.desk.id"
    )
    @Override
    DeskContributorDTO toDto(DeskContributor entity);

    @Mapping(
        target = "desk.id",
        source = "dto.desk"
    )
    @Override
    Collection<DeskContributor> toEntity(Collection<DeskContributorDTO> dto);

    @Mapping(
        target = "desk",
        source = "entities.desk.id"
    )
    @Override
    Collection<DeskContributorDTO> toDto(Collection<DeskContributor> entities);

    @Mapping(
        target = "desk.id",
        source = "dto.desk"
    )
    @Override
    List<DeskContributor> toEntity(List<DeskContributorDTO> dto);

    @Mapping(
        target = "desk",
        source = "entities.desk.id"
    )
    @Override
    List<DeskContributorDTO> toDto(List<DeskContributor> entities);
}
