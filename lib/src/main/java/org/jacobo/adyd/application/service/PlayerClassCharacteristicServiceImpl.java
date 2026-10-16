package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.jacobo.adyd.domain.service.PlayerClassCharacteristicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerClassCharacteristicServiceImpl implements PlayerClassCharacteristicService {

    private final PlayerClassCharacteristicRepository playerCharacteristicRepository;


    @Override
    @PreAuthorize("hasAnyRole('admin', 'master')")
    public PlayerClassCharacteristicModel save(PlayerClassCharacteristicModel playerClassCharacteristic) {
        return playerCharacteristicRepository.save(playerClassCharacteristic);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public PlayerClassCharacteristicModel getPlayerClassCharacteristics(Long playerClassId) {
        return playerCharacteristicRepository.findByPlayerClassId(playerClassId);
    }
}
