package ru.fedin.treloclient.mappers;

import org.mapstruct.Mapper;
import ru.fedin.treloclient.dtos.requests.DeskContributorReq;
import ru.fedin.treloclient.dtos.response.DeskContributorRes;

@Mapper(componentModel = "spring")
public interface ContributorMapper extends DataMapper<DeskContributorRes, DeskContributorReq>{
}
