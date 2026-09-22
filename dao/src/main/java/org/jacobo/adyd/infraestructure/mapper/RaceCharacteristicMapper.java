package org.jacobo.adyd.infraestructure.mapper;


import lombok.val;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.CharacteristicsEntity;
import org.jacobo.adyd.infraestructure.entities.RaceCharacteristicsEntity;
import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RaceCharacteristicMapper.class, CharacteristicMapper.class, RaceMapper.class})
public interface RaceCharacteristicMapper {

    @Mapping(target = "race", source = "race")
    @Mapping(target = "characteristic", source = "characteristic")
    @Mapping(target = "value", source = "characteristic.value")
    @Mapping(target = "symbol", source = "characteristic.symbol")
    RaceCharacteristicsEntity toEntity(RaceCharacteristicModel raceCharacteristicModel);

    @Mapping(target = "characteristic.value", source = "value")
    @Mapping(target = "characteristic.symbol", source = "symbol")
    RaceCharacteristicModel toModel(RaceCharacteristicsEntity raceCharacteristicEntity);

    List<RaceCharacteristicModel> toModel(List<RaceCharacteristicsEntity> raceCharacteristicEntities);

    List<RaceCharacteristicsEntity> toEntity(List<RaceCharacteristicModel> raceCharacteristicModels);

    default RaceCharacteristicModel mapToModel(Map<RaceEntity, List<RaceCharacteristicsEntity>> raceEntityListMap,
                                               @Context RaceMapper raceMapper,
                                               @Context CharacteristicMapper characteristicMapper){
        val raceCharacteristicModel = new RaceCharacteristicModel();
        raceCharacteristicModel.setRace(raceMapper.toDomain(raceEntityListMap.keySet().iterator().next()));
        raceCharacteristicModel.setCharacteristicsList(raceEntityListMap.values().stream()
                .flatMap(List::stream)
                .map(row -> {
                    CharacteristicModel characteristicModel = characteristicMapper.toCharacteristic(row.getCharacteristic());
                    characteristicModel.setValue(row.getValue());
                    characteristicModel.setSymbol(row.getSymbol());
                    return characteristicModel;
                })
                .collect(Collectors.toList()));
        return raceCharacteristicModel;
    }
}
