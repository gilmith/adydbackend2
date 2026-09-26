package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.ConflictRunTimeException;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.service.CharacteristicService;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.jacobo.adyd.domain.service.RaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final RaceCharacteristicService raceCharacteristicService;
    private final RaceService raceService;

    @Override
    @Transactional
    public CharacteristicModel createNewProperty(Long raceId, CharacteristicModel characteristicModel, String value) {
        val raceModel = raceService.getRaceById(raceId);
        val currentCharacteristicModel = characteristicRepository.findByCodeAndRace(characteristicModel.getCode(), raceId);
        if (isBuiltIn(characteristicModel)) {
            createNewBuiltInCharacteristic(characteristicModel, value, currentCharacteristicModel, raceModel);
        } else {
            createNewCustomCharacteristic(characteristicModel, value, currentCharacteristicModel, raceModel);
        }
        return characteristicModel;
    }

    private void createNewCustomCharacteristic(CharacteristicModel characteristicModel, String value, Optional<CharacteristicModel> currentCharacteristicModel, RaceModel raceModel) {
        currentCharacteristicModel.ifPresent(characteristic -> {
            throw new ConflictRunTimeException("Race Characteristic already exists");
        });
        val characteristicToPersist = characteristicRepository.saveNewCharacteristic(characteristicModel, raceModel);
        characteristicToPersist.setValue(value);
        characteristicToPersist.setSymbol(resolveSymbol(value));
        characteristicModel.setBuiltIn(false);
        val raceCharacteristic = RaceCharacteristicModel.builder()
                .characteristic(characteristicToPersist)
                .race(raceModel)
                .build();
        raceCharacteristicService.save(raceCharacteristic);
    }

    private void createNewBuiltInCharacteristic(CharacteristicModel characteristicModel, String value, Optional<CharacteristicModel> currentCharacteristicModel, RaceModel raceModel) {
        currentCharacteristicModel.ifPresent(characteristic -> {
            throw new ConflictRunTimeException("Race Characteristic already exists");
        });
        val characteristicToPersist = BuiltInCharacteristicsEnum.valueOf(characteristicModel.getCode())
                .getCharacteristicMeta();
        characteristicToPersist.setDescription(characteristicModel.getDescription());
        characteristicToPersist.setShortDescription(characteristicModel.getShortDescription());
        val persistedCharacteristic = characteristicRepository.save(characteristicToPersist);
        persistedCharacteristic.setValue(value);
        persistedCharacteristic.setSymbol(resolveSymbol(value));
        val raceCharacteristic = RaceCharacteristicModel.builder()
                .characteristic(persistedCharacteristic)
                .race(raceModel)
                .build();
        raceCharacteristicService.save(raceCharacteristic);
    }

    private SymbolTypeEnum resolveSymbol(String value) {
        try {
            return Integer.parseInt(value) > 0 ? SymbolTypeEnum.BONUS : SymbolTypeEnum.MALUS;
        } catch (NumberFormatException e) {
            return SymbolTypeEnum.NONE;
        }
    }

    private Boolean isBuiltIn(CharacteristicModel characteristicModel) {
        return Arrays.stream(BuiltInCharacteristicsEnum.values()).anyMatch(it -> {
            CharacteristicModel meta = it.getCharacteristicMeta();
            return characteristicModel.getCode().equals(meta.getCode())
                    || characteristicModel.getCode().equals(meta.getName());
        });
    }

    @Override
    public Optional<CharacteristicModel> findByCode(String code) {
        return characteristicRepository.findByCode(code);
    }

    @Override
    public List<String> getBuiltInCharacteristics() {
        return Arrays.stream(BuiltInCharacteristicsEnum.values()).map(it -> it.getCharacteristicMeta().getCode()).toList();
    }
}