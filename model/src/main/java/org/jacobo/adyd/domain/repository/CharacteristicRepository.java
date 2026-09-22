package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.CharacteristicModel;

import java.util.Optional;

public interface CharacteristicRepository {

    CharacteristicModel save(CharacteristicModel characteristicModel);

    Optional<CharacteristicModel> findByCode(String code);

    Optional<CharacteristicModel> findById(Long id);

    Optional<CharacteristicModel> findByCodeAndRace(String code, Long raceId);
}