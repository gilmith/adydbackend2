package org.jacobo.adyd.infraestructure.controller.staticgame;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jacobo.adyd.api.CharacteristicControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicBuiltInDto;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CharacteristicController implements CharacteristicControllerApi {

    private final CharacteristicService characteristicService;
    private final CharacteristicDtoMapper characteristicDtoMapper;

    @Override
    public ResponseEntity<CharacteristicDto> createNewCharacteristic(
           String xAPIVersion,
           CharacteristicDto characteristicDto
    ){
        return null;
    }

    @Override
    public ResponseEntity<List<CharacteristicBuiltInDto>> getBuiltin(String xAPIVersion) {
        return ResponseEntity.ok(characteristicDtoMapper.toDtos(characteristicService.getBuiltInCharacteristics()));
    }



}
