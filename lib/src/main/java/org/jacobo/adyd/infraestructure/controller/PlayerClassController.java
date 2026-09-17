package org.jacobo.adyd.infraestructure.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.PlayerClassControllerApi;
import org.jacobo.adyd.api.dto.PlayerClassDto;
import org.jacobo.adyd.domain.service.PlayerClassService;
import org.jacobo.adyd.infraestructure.mapper.PlayerClassDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerClassController implements PlayerClassControllerApi {

    private final PlayerClassDtoMapper playerClassDtoMapper;
    private final PlayerClassService playerClassService;

    @Override
    public ResponseEntity<List<PlayerClassDto>> getAllPlayerClass(){
        return ResponseEntity.ok(playerClassDtoMapper.toDtoList(playerClassService.getAll()));
    }

    @Override
    public ResponseEntity<PlayerClassDto> getPlayerClassById(Long id){
        return ResponseEntity.ok(playerClassDtoMapper.toDto(playerClassService.getById(id)));
    }

    @Override
    public ResponseEntity<PlayerClassDto> createNewPlayerClass(PlayerClassDto playerClassDto) {
        return ResponseEntity.ok(playerClassDtoMapper.toDto(playerClassService.createNewPlayerClass(playerClassDtoMapper.toModel(playerClassDto))));
    }
    @Override
    public ResponseEntity<Void> deletePlayerClassById(Long playerClassId) {
        playerClassService.deleteById(playerClassId);
        return ResponseEntity.noContent().build();
    }

}
