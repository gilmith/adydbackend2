package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
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
        if (isBuiltIn(characteristicModel)){
            createNewBuiltInCharacteristic(characteristicModel, value, currentCharacteristicModel, raceModel);
        } else {
            //TBC propiedades dinamicas
        }
        return characteristicModel;
    }

    private void createNewBuiltInCharacteristic(CharacteristicModel characteristicModel, String value, Optional<CharacteristicModel> currentCharacteristicModel, RaceModel raceModel) {
        if (currentCharacteristicModel.isEmpty()){
            val persistedCharacteristic = characteristicRepository.save(BuiltInCharacteristicsEnum.valueOf(characteristicModel.getCode()).getCharacteristicMeta());
            persistedCharacteristic.setValue(value);
            persistedCharacteristic.setSymbol(resolveSymbol(value));
            val raceCharacteristic  = RaceCharacteristicModel.builder()
                    .characteristic(persistedCharacteristic)
                    .race(raceModel)
                    .build();
            raceCharacteristicService.save(raceCharacteristic);
        } else {
            //esto esta mal es una modificacion el isEmpty esta a false
//            val raceCharacteristic  = RaceCharacteristicModel.builder()
//                    .characteristic(currentCharacteristicModel.get())
//                    .race(raceModel)
//                    .value(value)
//                    .symbol(resolveSymbol(value))
//                    .build();
//            raceCharacteristicService.save(raceCharacteristic);
        }
    }

    private SymbolTypeEnum resolveSymbol(String value) {
        try{
            return Integer.parseInt(value) >0 ? SymbolTypeEnum.BONUS : SymbolTypeEnum.MALUS;
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
}