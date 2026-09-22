package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.model.RaceModel;

public interface RaceCharacteristicService {

    RaceCharacteristicModel save(RaceCharacteristicModel characteristicModel);

    RaceCharacteristicModel getRaceById(Long raceId);
}
