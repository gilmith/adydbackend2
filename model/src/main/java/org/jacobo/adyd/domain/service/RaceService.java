package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.RaceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RaceService {

    Page<RaceModel> getAllRaces(PageRequest pagination);

    RaceModel createNewRace(RaceModel raceModel);

    void deleteRace(Long id);

    RaceModel getRaceById(Long raceId);

    RaceModel updateRace(Long raceId, RaceModel raceModel);

}
