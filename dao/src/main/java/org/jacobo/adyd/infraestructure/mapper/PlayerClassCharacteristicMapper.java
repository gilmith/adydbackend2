package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.jacobo.adyd.infraestructure.entities.PlayerClassCharacteristicEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PlayerClassMapper.class, CharacteristicMapper.class})
public interface PlayerClassCharacteristicMapper {

    PlayerClassCharacteristicEntity toEntity(PlayerClassCharacteristicModel dto);

    PlayerClassCharacteristicModel toModel(PlayerClassCharacteristicEntity entity);



}
