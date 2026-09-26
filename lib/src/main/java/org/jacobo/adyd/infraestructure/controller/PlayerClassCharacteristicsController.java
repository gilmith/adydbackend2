package org.jacobo.adyd.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.PlayerClassCharacteristicsControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.api.dto.PlayerClassCharacteristicDto;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerClassCharacteristicsController implements PlayerClassCharacteristicsControllerApi {

    private final CharacteristicService characteristicService;
    private final CharacteristicDtoMapper characteristicDtoMapper;


    @Override
    public ResponseEntity<List<PlayerClassCharacteristicDto>> getPlayerClassCharacteristics(Long playerClassId){
        return null;
    }

    @Override
    public ResponseEntity<PlayerClassCharacteristicDto> addCharacteristicToPlayerClass(Long playerClassId, CharacteristicDto playerClassCharacteristicDto) {
        return ResponseEntity.ok(characteristicDtoMapper.toDto(  characteristicService.createNewPropertyPlayer(
                playerClassId, characteristicDtoMapper.toModel(playerClassCharacteristicDto), playerClassCharacteristicDto.getValue())));
    }
}
