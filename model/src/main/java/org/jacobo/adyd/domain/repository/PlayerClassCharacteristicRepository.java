package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;

public interface PlayerClassCharacteristicRepository {

    PlayerClassCharacteristicModel save(PlayerClassCharacteristicModel playerClassCharacteristicModel);

    PlayerClassCharacteristicModel findByPlayerClassId(Long playerClassId);

}
