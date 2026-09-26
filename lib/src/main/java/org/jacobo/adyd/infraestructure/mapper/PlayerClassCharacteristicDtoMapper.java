package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.PlayerClassCharacteristicDto;
import org.jacobo.adyd.domain.model.PlayerClassCharacteristicModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerClassCharacteristicDtoMapper {

    PlayerClassCharacteristicModel toModel(PlayerClassCharacteristicDto dto);

    PlayerClassCharacteristicDto toDto(PlayerClassCharacteristicModel model);

}
