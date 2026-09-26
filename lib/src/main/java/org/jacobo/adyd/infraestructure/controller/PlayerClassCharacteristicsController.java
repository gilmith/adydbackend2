package org.jacobo.adyd.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.PlayerClassCharacteristicsControllerApi;
import org.jacobo.adyd.api.dto.PlayerClassCharacteristicDto;
import org.jacobo.adyd.domain.service.PlayerClassCharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.PlayerClassCharacteristicDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerClassCharacteristicsController implements PlayerClassCharacteristicsControllerApi {

    private final PlayerClassCharacteristicDtoMapper playerClassCharacteristicDtoMapper;
    private final PlayerClassCharacteristicService playerClassCharacteristicService;

    @Override
    public ResponseEntity<List<PlayerClassCharacteristicDto>> getPlayerClassCharacteristics(Long playerClassId){
        return null;
    }

    @Override
    public ResponseEntity<PlayerClassCharacteristicDto> createPlayerClassCharacteristic(Long playerClassId, PlayerClassCharacteristicDto playerClassCharacteristicDto) {
        return ResponseEntity.ok(
                playerClassCharacteristicDtoMapper.toDto(
                playerClassCharacteristicService.createNewPlayerCharacteristic(
                playerClassCharacteristicDtoMapper.toModel(playerClassCharacteristicDto))));
    }
}
