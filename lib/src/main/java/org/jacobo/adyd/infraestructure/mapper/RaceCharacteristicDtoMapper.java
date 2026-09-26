package org.jacobo.adyd.infraestructure.mapper;


import org.jacobo.adyd.api.dto.RaceCharacteristicDto;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RaceDtoMapper.class, CharacteristicDtoMapper.class})
public interface RaceCharacteristicDtoMapper {

    @Mapping(target = "race", source = "raceCharacteristicModel.race")
    @Mapping(target = "characteristic", source = "raceCharacteristicModel.characteristicsList")
    RaceCharacteristicDto toDto(RaceCharacteristicModel raceCharacteristicModel);

}