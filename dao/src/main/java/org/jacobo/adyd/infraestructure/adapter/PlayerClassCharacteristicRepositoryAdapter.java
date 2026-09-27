package org.jacobo.adyd.infraestructure.adapter;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.jacobo.adyd.infraestructure.mapper.PlayerClassCharacteristicMapper;
import org.jacobo.adyd.infraestructure.persistence.PlayerClassCharacteristicJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PlayerClassCharacteristicRepositoryAdapter implements PlayerClassCharacteristicRepository {

    private final PlayerClassCharacteristicJpaRepository playerClassCharacteristicJpaRepository;
    private final PlayerClassCharacteristicMapper playerClassCharacteristicMapper;

    @Override
    public PlayerClassCharacteristicModel save(PlayerClassCharacteristicModel playerClassCharacteristicModel) {
        return  playerClassCharacteristicMapper
                .toModel(playerClassCharacteristicJpaRepository.save(
                        playerClassCharacteristicMapper.toEntity(playerClassCharacteristicModel)));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerClassCharacteristicModel findByPlayerClassId(Long playerClassId) {
        val listPlayerClassCharacteristic = playerClassCharacteristicJpaRepository.findAllByPlayerClassId(playerClassId);
        if (listPlayerClassCharacteristic.isEmpty()) {
            throw new NotFoundRunTimeException("Player class characteristic not found");
        }
        val playerClassModel = playerClassCharacteristicMapper.toModel(listPlayerClassCharacteristic.getFirst()).getPlayerClass();
        val characteristics = listPlayerClassCharacteristic.stream()
                .map(playerClassCharacteristicMapper::toCharacteristicModel)
                .toList();
        val playerClassCharacteristicModel = new PlayerClassCharacteristicModel();
        playerClassCharacteristicModel.setPlayerClass(playerClassModel);
        playerClassCharacteristicModel.setCharacteristics(characteristics);
        return playerClassCharacteristicModel;
    }
}
