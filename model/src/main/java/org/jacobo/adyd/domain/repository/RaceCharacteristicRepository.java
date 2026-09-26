package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.RaceCharacteristicModel;

public interface RaceCharacteristicRepository {

    RaceCharacteristicModel save(RaceCharacteristicModel raceCharacteristicModel);

    RaceCharacteristicModel findByRaceId(Long raceId);

    RaceCharacteristicModel findByRaceIdAndCharacteristicId(Long raceId, Long characteristicId);
}