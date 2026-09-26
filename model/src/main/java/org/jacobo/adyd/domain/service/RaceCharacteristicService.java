package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.RaceCharacteristicModel;

public interface RaceCharacteristicService {

    RaceCharacteristicModel save(RaceCharacteristicModel characteristicModel);

    RaceCharacteristicModel getRaceById(Long raceId);

    RaceCharacteristicModel findRaceCharacteristicByRaceIdAndCharacteristicId(Long raceId, Long characteristicId);
}
