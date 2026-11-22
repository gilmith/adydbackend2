package org.jacobo.adyd.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.PlayerClassControllerApi;
import org.jacobo.adyd.api.dto.CharacteristicBuiltInDto;
import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.api.dto.PlayerClassDto;
import org.jacobo.adyd.domain.service.PlayerClassService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.PageMapper;
import org.jacobo.adyd.infraestructure.mapper.PlayerClassDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerClassController implements PlayerClassControllerApi {

    private final PlayerClassDtoMapper playerClassDtoMapper;
    private final PlayerClassService playerClassService;
    private final CharacteristicDtoMapper characteristicDtoMapper;

    @Override
    public ResponseEntity<PaginatedDto> getAllPlayerClass(
            String xAPIVersion,
            Integer page,
            Integer size,
            String sort
    ){
        val pagination = PageMapper.of(page, size, sort);
        return ResponseEntity.ok(playerClassDtoMapper.toDto(playerClassService.getAll(pagination)));
    }

    @Override
    public ResponseEntity<PlayerClassDto> getPlayerClassById(Long id, String xAPIVersion){
        return ResponseEntity.ok(playerClassDtoMapper.toDto(playerClassService.getById(id)));
    }

    @Override
    public ResponseEntity<PlayerClassDto> createNewPlayerClass(String xAPIVersion, PlayerClassDto playerClassDto) {
        return ResponseEntity.ok(playerClassDtoMapper.toDto(playerClassService.createNewPlayerClass(playerClassDtoMapper.toModel(playerClassDto))));
    }
    @Override
    public ResponseEntity<Void> deletePlayerClassById(Long playerClassId, String xAPIVersion) {
        playerClassService.deleteById(playerClassId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CharacteristicBuiltInDto>> getPlayerClassBuiltIn(String xAPIVersion) {
        return ResponseEntity.ok(characteristicDtoMapper.toDtos(playerClassService.getAllBuiltIn()));
    }

}
