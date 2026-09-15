package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.RaceModel;

import java.util.List;

public interface RaceService {

    List<RaceModel> getAllRaces();

    RaceModel createNewRace(RaceModel raceModel);
}
