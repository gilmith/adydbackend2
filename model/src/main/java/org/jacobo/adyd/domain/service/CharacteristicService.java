package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CharacteristicModel;

import java.util.Optional;

public interface CharacteristicService {

    CharacteristicModel createNewProperty(Long raceId, CharacteristicModel characteristicModel, String value);

    Optional<CharacteristicModel> findByCode(String code);
}