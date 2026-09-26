package org.jacobo.adyd.infraestructure.mapper;

import lombok.val;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.RaceCharacteristicsEntity;
import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RaceMapper.class, CharacteristicMapper.class})
public interface RaceCharacteristicMapper {

    @Mapping(target = "race", source = "race")
    @Mapping(target = "characteristic", source = "characteristic")
    @Mapping(target = "value", source = "characteristic.value")
    @Mapping(target = "symbol", source = "characteristic.symbol")
    RaceCharacteristicsEntity toEntity(RaceCharacteristicModel raceCharacteristicModel);

    @Mapping(target = "characteristic.value", source = "value")
    @Mapping(target = "characteristic.symbol", source = "symbol")
    RaceCharacteristicModel toRaceModel(RaceCharacteristicsEntity raceCharacteristicEntity);

    List<RaceCharacteristicModel> toRaceModel(List<RaceCharacteristicsEntity> raceCharacteristicEntities);

    List<RaceCharacteristicsEntity> toEntity(List<RaceCharacteristicModel> raceCharacteristicModels);

    @Mapping(target = ".", source = "characteristic")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "symbol", source = "symbol")
    CharacteristicModel toCharacteristicModel(RaceCharacteristicsEntity entity);

    default RaceCharacteristicModel mapToModel(Map<RaceEntity, List<RaceCharacteristicsEntity>> raceEntityListMap) {
        if (raceEntityListMap == null || raceEntityListMap.isEmpty()) {
            return null;
        }

        val entry = raceEntityListMap.entrySet().iterator().next();
        val raceEntity = entry.getKey();
        val entitiesList = entry.getValue();

        val characteristicsList = entitiesList.stream()
                .map(this::toCharacteristicModel)
                .collect(Collectors.toList());

        val raceModel = toRaceModel(entitiesList.getFirst()).getRace();

        val raceCharacteristicModel = new RaceCharacteristicModel();
        raceCharacteristicModel.setRace(raceModel);
        raceCharacteristicModel.setCharacteristicsList(characteristicsList);

        return raceCharacteristicModel;
    }
}