package org.jacobo.adyd.application.service.helpers;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.eav.EavCharacteristicCreateHelper;
import org.jacobo.adyd.domain.model.BuiltInPlayerCharacteristicsEnum;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.repository.PlayerClassCharacteristicRepository;
import org.jacobo.adyd.domain.service.PlayerClassService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerClassCharacteristicHelper extends EavCharacteristicCreateHelper<Long, PlayerClassModel, PlayerClassCharacteristicModel> {

    private final PlayerClassService playerClassService;
    private final CharacteristicRepository characteristicRepository;
    private final PlayerClassCharacteristicRepository playerClassCharacteristicRepository;

    @Override
    protected PlayerClassModel loadOwner(Long playerClassId) {
        return playerClassService.getPlayerClassById(playerClassId);
    }

    @Override
    protected Optional<CharacteristicModel> findCurrentCharacteristic(Long playerClassId, String code) {
        return characteristicRepository.findByCodeAndPlayerClass(code, playerClassId);
    }

    @Override
    protected List<CharacteristicModel> builtInMetas() {
        return Arrays.stream(BuiltInPlayerCharacteristicsEnum.values())
                .map(BuiltInPlayerCharacteristicsEnum::getCharacteristicMeta)
                .toList();
    }

    @Override
    protected Optional<CharacteristicModel> resolveBuiltInMeta(CharacteristicModel model) {
        return Optional.of(BuiltInPlayerCharacteristicsEnum.valueOf(model.getCode()).getCharacteristicMeta());
    }

    @Override
    protected CharacteristicModel persistStandard(CharacteristicModel model) {
        return characteristicRepository.save(model);
    }

    @Override
    protected CharacteristicModel persistCustom(CharacteristicModel model, PlayerClassModel owner) {
        return characteristicRepository.saveNewCharacteristic(model, owner);
    }

    @Override
    protected PlayerClassCharacteristicModel buildRelation(PlayerClassModel owner, CharacteristicModel characteristic) {
        return PlayerClassCharacteristicModel.builder()
                .playerClass(owner)
                .characteristic(characteristic)
                .build();
    }

    @Override
    protected PlayerClassCharacteristicModel saveRelation(PlayerClassCharacteristicModel relation) {
        return playerClassCharacteristicRepository.save(relation);
    }

    @Override
    protected PlayerClassCharacteristicModel findRelationByOwnerId(Long playerClassId) {
        return playerClassCharacteristicRepository.findByPlayerClassId(playerClassId);
    }

    @Override
    protected List<CharacteristicModel> extractCharacteristics(PlayerClassCharacteristicModel relation) {
        return relation.getCharacteristics();
    }

    @Override
    protected String conflictSubject() {
        return "Player Class Characteristic";
    }
}