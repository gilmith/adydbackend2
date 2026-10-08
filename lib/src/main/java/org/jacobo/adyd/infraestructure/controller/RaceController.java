package org.jacobo.adyd.infraestructure.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.api.RaceControllerApi;
import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.api.dto.RaceDto;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.PageMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class RaceController implements RaceControllerApi {

    private final RaceService raceService;
    private final RaceDtoMapper raceDtoMapper;

    @Override
    public ResponseEntity<PaginatedDto> getAllRaces(
            Integer page,
            Integer size,
            String sort
    ) {
        val pagination = PageMapper.of(page, size, sort);
        return ResponseEntity.ok(raceDtoMapper.toDto(raceService.getAllRaces(pagination)));
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
