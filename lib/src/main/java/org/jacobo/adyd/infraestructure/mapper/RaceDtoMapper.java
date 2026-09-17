package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.model.RaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RaceDtoMapper {

    RaceDto toDto(RaceModel race);

    List<RaceDto> toDto(List<RaceModel> races);


    RaceModel toModel(RaceDto raceDto);

    @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    RaceModel getModifiedRace(@MappingTarget RaceModel target, RaceModel source);
}
