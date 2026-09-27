package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.jacobo.adyd.domain.service.PlayerClassCharacteristicService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerClassCharacteristicServiceImpl implements PlayerClassCharacteristicService {

    private final PlayerClassCharacteristicRepository playerCharacteristicRepository;


    @Override
    public PlayerClassCharacteristicModel save(PlayerClassCharacteristicModel playerClassCharacteristic) {
        return playerCharacteristicRepository.save(playerClassCharacteristic);
    }

    @Override
    public PlayerClassCharacteristicModel getPlayerClassCharacteristics(Long playerClassId) {
        return playerCharacteristicRepository.findByPlayerClassId(playerClassId);
    }
}
