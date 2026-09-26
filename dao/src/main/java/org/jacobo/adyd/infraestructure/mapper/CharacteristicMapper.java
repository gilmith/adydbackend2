package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.CharacteristicsEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacteristicMapper {

    CharacteristicsEntity toEntity(CharacteristicModel characteristic);

    CharacteristicModel toCharacteristic(CharacteristicsEntity characteristicEntity);

    List<CharacteristicModel> toCharacteristicList(List<CharacteristicsEntity> characteristicEntities);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", ignore = true)
    CharacteristicModel copyValues(@MappingTarget CharacteristicModel it, CharacteristicModel model);
}
