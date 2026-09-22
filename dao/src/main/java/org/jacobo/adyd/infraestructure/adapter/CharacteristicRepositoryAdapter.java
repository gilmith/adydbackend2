package org.jacobo.adyd.infraestructure.adapter;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
import org.jacobo.adyd.infraestructure.persistence.CharacteristicJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacteristicRepositoryAdapter implements CharacteristicRepository {

    private final CharacteristicJpaRepository characteristicJpaRepository;
    private final CharacteristicMapper characteristicMapper;

    @Override
    public CharacteristicModel save(CharacteristicModel characteristicModel) {
        return characteristicMapper.toCharacteristic(
                characteristicJpaRepository.save(characteristicMapper.toEntity(characteristicModel)));
    }

    @Override
    public Optional<CharacteristicModel> findByCode(String code) {
        return characteristicJpaRepository.findByCode(code).map(characteristicMapper::toCharacteristic);
    }

    @Override
    public Optional<CharacteristicModel> findById(Long id) {
        return characteristicJpaRepository.findById(id).map(characteristicMapper::toCharacteristic);
    }

    @Override
    public Optional<CharacteristicModel> findByCodeAndRace(String code, Long raceId) {
        return characteristicJpaRepository.findByCodeAndRace(code, raceId).map(characteristicMapper::toCharacteristic);
    }
}