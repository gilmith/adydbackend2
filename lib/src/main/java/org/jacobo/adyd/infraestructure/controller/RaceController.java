package org.jacobo.adyd.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.api.RaceControllerApi;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
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

    @Override
    public ResponseEntity<Void> deleteRace(Long raceId) {
        raceService.deleteRace(raceId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<RaceDto> getRaceById(Long raceId) {
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.getRaceById(raceId)));
    }

    @Override
    public ResponseEntity<RaceDto> modifyRace(Long raceId, RaceDto raceDto) {
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.updateRace(raceId, raceDtoMapper.toModel(raceDto))));
    }
}
