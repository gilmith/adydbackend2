package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.ConflictRunTimeException;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.service.*;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
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
    private final PlayerClassCharacteristicService playerClassCharacteristicService;
    private final RaceService raceService;
    private final CharacteristicMapper characteristicMapper;
    private final PlayerClassService playerClassService;

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



    @Override
    public CharacteristicModel createNewPropertyPlayer(Long playerClassId, CharacteristicModel characteristicModel, String value) {
        val playerClassModel = playerClassService.getPlayerClassById(playerClassId);
        val currentCharacteristicModel = characteristicRepository.findByCodeAndPlayerClass(characteristicModel.getCode(), playerClassId);
        if (isBuiltInPlayerClass(characteristicModel)) {
            createNewBuiltInCharacteristicForPlayerClass(characteristicModel, value, currentCharacteristicModel, playerClassModel);
        } else {
            createNewCustomCharacteristicForPlayerClass(characteristicModel, value, currentCharacteristicModel, playerClassModel);
        }
        return characteristicModel;
    }

    private void createNewBuiltInCharacteristicForPlayerClass(CharacteristicModel characteristicModel, String value, Optional<CharacteristicModel> currentCharacteristicModel, PlayerClassModel playerClassModel) {
        currentCharacteristicModel.ifPresent(characteristic -> {
            throw new ConflictRunTimeException("Player Class Characteristic already exists");
        });
        val characteristicToPersist = BuiltInPlayerCharacteristicsEnum.valueOf(characteristicModel.getCode())
                .getCharacteristicMeta();
        characteristicToPersist.setDescription(characteristicModel.getDescription());
        characteristicToPersist.setShortDescription(characteristicModel.getShortDescription());
        val persistedCharacteristic = characteristicRepository.save(characteristicToPersist);
        persistedCharacteristic.setValue(value);
        persistedCharacteristic.setSymbol(resolveSymbol(value));
        val playerClassCharacteristic = PlayerClassCharacteristicModel.builder()
                .characteristic(persistedCharacteristic)
                .playerClass(playerClassModel)
                .build();
        playerClassCharacteristicService.save(playerClassCharacteristic);

    }

    private void createNewCustomCharacteristicForPlayerClass(CharacteristicModel characteristicModel, String value, Optional<CharacteristicModel> currentCharacteristicModel, PlayerClassModel playerClassModel) {
        currentCharacteristicModel.ifPresent(characteristic -> {
            throw new ConflictRunTimeException("Race Characteristic already exists");
        });
        val characteristicToPersist = characteristicRepository.saveNewCharacteristic(characteristicModel, playerClassModel);
        characteristicToPersist.setValue(value);
        characteristicToPersist.setSymbol(resolveSymbol(value));
        characteristicModel.setBuiltIn(false);
        val playerClassCharacteristicModel = PlayerClassCharacteristicModel.builder()
                .characteristic(characteristicToPersist)
                .playerClass(playerClassModel)
                .build();
        playerClassCharacteristicService.save(playerClassCharacteristicModel);
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

    private boolean isBuiltInPlayerClass(CharacteristicModel characteristicModel) {
        return Arrays.stream(BuiltInPlayerCharacteristicsEnum.values()).anyMatch(it -> {
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

    @Override
    public CharacteristicModel updateCharacteristic(Long raceId, Long characteristicId, CharacteristicModel model) {
        val raceModel = raceService.getRaceById(raceId);
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