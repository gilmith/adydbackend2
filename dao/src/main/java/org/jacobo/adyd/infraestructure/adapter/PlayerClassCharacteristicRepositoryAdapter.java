package org.jacobo.adyd.infraestructure.adapter;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerClassCharacteristicRepositoryAdapter implements PlayerClassCharacteristicRepository {

    private final PlayerClassCharacteristicRepository playerClassCharacteristicRepository;


    @Override
    public PlayerClassCharacteristicModel save(PlayerClassCharacteristicModel playerClassCharacteristicModel) {
        return playerClassCharacteristicRepository.save(playerClassCharacteristicModel);
    }
}
