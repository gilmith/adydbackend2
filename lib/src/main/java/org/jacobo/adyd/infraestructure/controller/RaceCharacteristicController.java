package org.jacobo.adyd.infraestructure.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.RaceCharacteristicControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.api.dto.RaceCharacteristicDto;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceCharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RaceCharacteristicController implements RaceCharacteristicControllerApi {

    private final RaceCharacteristicService raceCharacteristicService;
    private final CharacteristicService characteristicService;
    private final RaceService raceService;
    private final CharacteristicDtoMapper characteristicDtoMapper;
    private final RaceDtoMapper raceDtoMapper;
    private final RaceCharacteristicDtoMapper raceCharacteristicDtoMapper;

    @Override
    public ResponseEntity<RaceDto> addCharacteristicToRace(Long raceId, CharacteristicDto characteristicDto) {
        val result = characteristicService.createNewProperty(raceId, characteristicDtoMapper.toModel(characteristicDto), characteristicDto.getValue());
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.getRaceById(raceId)));
    }

    @Override
    public ResponseEntity<RaceCharacteristicDto> getRaceCharacteristicsById(Long raceId){
        return ResponseEntity.ok(raceCharacteristicDtoMapper.toDto(raceCharacteristicService.getRaceById(raceId)));
    }

}