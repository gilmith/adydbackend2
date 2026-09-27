package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
import org.jacobo.adyd.application.service.helpers.PlayerClassCharacteristicHelper;
import org.jacobo.adyd.application.service.helpers.RaceCharacteristicHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final RaceCharacteristicService raceCharacteristicService;
    private final CharacteristicMapper characteristicMapper;
    private final RaceCharacteristicHelper raceCharacteristicHelper;
    private final PlayerClassCharacteristicHelper playerClassCharacteristicHelper;

    @Override
    @Transactional
    public CharacteristicModel createNewProperty(Long raceId, CharacteristicModel characteristicModel, String value) {
        return raceCharacteristicHelper.createNewProperty(raceId, characteristicModel, value);
    }

    @Override
    public CharacteristicModel createNewPropertyPlayer(Long playerClassId, CharacteristicModel characteristicModel, String value) {
        return playerClassCharacteristicHelper.createNewProperty(playerClassId, characteristicModel, value);
    }

    @Override
    public Optional<CharacteristicModel> findByCode(String code) {
        return characteristicRepository.findByCode(code);
    }

    @Override
    public List<String> getBuiltInCharacteristics() {
        return Arrays.stream(BuiltInCharacteristicsEnum.values()).map(it -> it.getCharacteristicMeta().getCode()).toList();
    }

    @Override
    public CharacteristicModel updateCharacteristic(Long raceId, Long characteristicId, CharacteristicModel model) {
        return characteristicRepository.findById(characteristicId)
                .map(it -> {
                    val characteristicToSave = characteristicMapper.copyValues(it, model);
                    val raceCharacteristic = raceCharacteristicService.findRaceCharacteristicByRaceIdAndCharacteristicId(raceId, characteristicId);
                    if (Objects.nonNull(model.getValue())){
                        characteristicToSave.setValue(model.getValue());
                    }
                    raceCharacteristic.setCharacteristic(characteristicToSave);
                    raceCharacteristicService.save(raceCharacteristic);
                    return characteristicRepository.save(characteristicToSave);
                })
                .orElseThrow(() -> new NotFoundRunTimeException("Characteristic not found"));
    }


}