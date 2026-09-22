package org.jacobo.adyd.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.CharacteristicControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CharacteristicController implements CharacteristicControllerApi {

    private final CharacteristicService characteristicService;
    private final CharacteristicDtoMapper characteristicDtoMapper;

    @Override
    public ResponseEntity<CharacteristicDto> createNewCharacteristic(CharacteristicDto characteristicDto) {
       return null;
    }

}