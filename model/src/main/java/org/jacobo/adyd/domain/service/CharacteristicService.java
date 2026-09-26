package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CharacteristicModel;

import java.util.List;
import java.util.Optional;

public interface CharacteristicService {

    CharacteristicModel createNewProperty(Long raceId, CharacteristicModel characteristicModel, String value);

    Optional<CharacteristicModel> findByCode(String code);

    List<String> getBuiltInCharacteristics();

    CharacteristicModel updateCharacteristic(Long raceId, Long characteristicId, CharacteristicModel model);
}