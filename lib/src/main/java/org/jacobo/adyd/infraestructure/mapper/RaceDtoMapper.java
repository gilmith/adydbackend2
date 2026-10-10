package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.model.RaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface RaceDtoMapper {

    RaceDto toDto(RaceModel race);

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