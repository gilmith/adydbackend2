package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.domain.service.RaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Override
    public List<RaceModel> getAllRaces() {
        return raceRepository.findAll();
    }

    @Override
    public RaceModel createNewRace(RaceModel raceModel) {
        return raceRepository.save(raceModel);
    }
}
