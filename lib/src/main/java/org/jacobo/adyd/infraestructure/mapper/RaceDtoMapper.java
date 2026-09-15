package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.model.RaceModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RaceDtoMapper {

    RaceDto toDto(RaceModel race);

    List<RaceDto> toDto(List<RaceModel> races);


    RaceModel toModel(RaceDto raceDto);
}
