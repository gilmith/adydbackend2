package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.PlayerClassCharacteristicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlayerClassMapper.class, CharacteristicMapper.class})
public interface PlayerClassCharacteristicMapper {

    @Mapping(target = "characteristic", source = "characteristic")
    @Mapping(target = "value", source = "characteristic.value")
    @Mapping(target = "symbol", source = "characteristic.symbol")
    PlayerClassCharacteristicEntity toEntity(PlayerClassCharacteristicModel model);

    @Mapping(target = "characteristic.value", source = "value")
    @Mapping(target = "characteristic.symbol", source = "symbol")
    PlayerClassCharacteristicModel toModel(PlayerClassCharacteristicEntity entity);

    @Mapping(target = ".", source = "characteristic")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "symbol", source = "symbol")
    CharacteristicModel toCharacteristicModel(PlayerClassCharacteristicEntity entity);

}
