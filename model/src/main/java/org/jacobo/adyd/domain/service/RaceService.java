package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.validator.SortValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RaceService {

    @SortValidator(RaceModel.class)
    Page<RaceModel> getAllRaces(PageRequest pagination);

    RaceModel createNewRace(RaceModel raceModel);

    void deleteRace(Long id);

    RaceModel getRaceById(Long raceId);

    RaceModel updateRace(Long raceId, RaceModel raceModel);

}
