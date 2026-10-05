package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.api.dto.PaginatedDtoContentInner;
import org.jacobo.adyd.api.dto.PlayerClassDto;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.model.RaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RaceDtoMapper {

    RaceDto toDto(RaceModel race);

    @Mapping(target = "fileStoreUrl", ignore = true)
    @Mapping(target = "hitDice", ignore = true)
    PaginatedDtoContentInner toContentInner(RaceModel campaign);

    RaceModel toModel(RaceDto raceDto);

    @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    RaceModel getModifiedRace(@MappingTarget RaceModel target, RaceModel source);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "content", source = "content")
    PaginatedDto toDto(Page<RaceModel> raceModels);

}
