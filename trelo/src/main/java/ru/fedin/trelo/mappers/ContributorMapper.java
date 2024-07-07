package ru.fedin.trelo.mappers;

import org.mapstruct.Mapper;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.eintites.DeskContributor;

@Mapper(componentModel = "spring")
public interface ContributorMapper extends DataMapper<DeskContributor, DeskContributorDTO>{
}
