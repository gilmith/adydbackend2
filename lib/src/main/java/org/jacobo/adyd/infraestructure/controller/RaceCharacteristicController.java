package org.jacobo.adyd.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.RaceCharacteristicControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.api.dto.CharacteristicToModifyDto;
import org.jacobo.adyd.api.dto.RaceCharacteristicDto;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceCharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public ResponseEntity<List<String>> getBuiltInCharacteristics(){
        return ResponseEntity.ok(characteristicService.getBuiltInCharacteristics());
    }
    @Override
    public ResponseEntity<CharacteristicDto> updateRaceCharacteristic(Long raceId, Long characteristicId, CharacteristicToModifyDto characteristicToModifyDto) {
        val result = characteristicService.updateCharacteristic(raceId, characteristicId, characteristicDtoMapper.toModel(characteristicToModifyDto));
        return ResponseEntity.ok(characteristicDtoMapper.toDto(result));
    }
}