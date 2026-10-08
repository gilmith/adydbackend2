package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.domain.service.RaceService;
import org.jacobo.adyd.infraestructure.mapper.RaceDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceDtoMapper raceDtoMapper;

    @Override
    public Page<RaceModel> getAllRaces(PageRequest pagination) {
        return raceRepository.findAll(pagination);
    }

    @Override
    public RaceModel createNewRace(RaceModel raceModel) {
        return raceRepository.save(raceModel);
    }

    @Override
    public void deleteRace(Long id) {
        raceRepository.deleteById(id);
    }

    @Override
    public RaceModel getRaceById(Long raceId) {
        return raceRepository.findById(raceId).orElseThrow(() -> new NotFoundRunTimeException("Race id not found"));
    }

    @Override
    public RaceModel updateRace(Long raceId, RaceModel raceModel) {
        val currentRace = raceRepository.findById(raceId).orElseThrow(() -> new NotFoundRunTimeException("Race id not found"));
        return raceRepository.save(raceDtoMapper.getModifiedRace(currentRace, raceModel));
    }
}
