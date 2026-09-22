package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.CharacteristicsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacteristicMapper {

    CharacteristicsEntity toEntity(CharacteristicModel characteristic);

    CharacteristicModel toCharacteristic(CharacteristicsEntity characteristicEntity);

    List<CharacteristicModel> toCharacteristicList(List<CharacteristicsEntity> characteristicEntities);
}
