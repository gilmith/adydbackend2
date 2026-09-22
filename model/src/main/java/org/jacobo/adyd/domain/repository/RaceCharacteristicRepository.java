package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.RaceCharacteristicModel;

import java.util.Optional;

public interface RaceCharacteristicRepository {

    RaceCharacteristicModel save(RaceCharacteristicModel raceCharacteristicModel);

    RaceCharacteristicModel findByRaceId(Long raceId);
}