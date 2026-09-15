package org.jacobo.adyd.infraestructure.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.RaceControllerApi;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RaceController implements RaceControllerApi {

    private final RaceService raceService;
    private final RaceDtoMapper raceDtoMapper;

    @Override
    public ResponseEntity<List<RaceDto>> getAllRaces() {
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.getAllRaces()));
    }

    @Override
    public ResponseEntity<RaceDto> createNewRace(RaceDto raceDto){
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.createNewRace(raceDtoMapper.toModel(raceDto))));
    }
}
