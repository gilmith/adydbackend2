package org.jacobo.adyd.application.service.helpers;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.eav.EavCharacteristicCreateHelper;
import org.jacobo.adyd.domain.model.BuiltInCharacteristicsEnum;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.repository.CharacteristicRepository;
import org.jacobo.adyd.domain.repository.RaceCharacteristicRepository;
import org.jacobo.adyd.domain.service.RaceService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RaceCharacteristicHelper extends EavCharacteristicCreateHelper<Long, RaceModel, RaceCharacteristicModel> {

    private final RaceService raceService;
    private final CharacteristicRepository characteristicRepository;
    private final RaceCharacteristicRepository raceCharacteristicRepository;

    @Override
    protected RaceModel loadOwner(Long raceId) {
        return raceService.getRaceById(raceId);
    }

    @Override
    protected Optional<CharacteristicModel> findCurrentCharacteristic(Long raceId, String code) {
        return characteristicRepository.findByCodeAndRace(code, raceId);
    }

    @Override
    protected List<CharacteristicModel> builtInMetas() {
        return Arrays.stream(BuiltInCharacteristicsEnum.values())
                .map(BuiltInCharacteristicsEnum::getCharacteristicMeta)
                .toList();
    }

    @Override
    protected Optional<CharacteristicModel> resolveBuiltInMeta(CharacteristicModel model) {
        return Optional.of(BuiltInCharacteristicsEnum.valueOf(model.getCode()).getCharacteristicMeta());
    }

    @Override
    protected CharacteristicModel persistStandard(CharacteristicModel model) {
        return characteristicRepository.save(model);
    }

    @Override
    protected CharacteristicModel persistCustom(CharacteristicModel model, RaceModel owner) {
        return characteristicRepository.saveNewCharacteristic(model, owner);
    }

    @Override
    protected RaceCharacteristicModel buildRelation(RaceModel owner, CharacteristicModel characteristic) {
        return RaceCharacteristicModel.builder()
                .race(owner)
                .characteristic(characteristic)
                .build();
    }

    @Override
    protected RaceCharacteristicModel saveRelation(RaceCharacteristicModel relation) {
        return raceCharacteristicRepository.save(relation);
    }

    @Override
    protected RaceCharacteristicModel findRelationByOwnerId(Long raceId) {
        return raceCharacteristicRepository.findByRaceId(raceId);
    }

    @Override
    protected List<CharacteristicModel> extractCharacteristics(RaceCharacteristicModel relation) {
        return relation.getCharacteristicsList();
    }

    @Override
    protected String conflictSubject() {
        return "Race Characteristic";
    }
}