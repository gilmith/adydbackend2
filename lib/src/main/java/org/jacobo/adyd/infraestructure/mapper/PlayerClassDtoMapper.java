package org.jacobo.adyd.infraestructure.mapper;


import org.jacobo.adyd.api.dto.PlayerClassDto;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CampaignDtoMapper.class})
public interface PlayerClassDtoMapper {

    PlayerClassModel toModel(PlayerClassDto playerClassDto);

    PlayerClassDto toDto(PlayerClassModel playerClassModel);

    List<PlayerClassDto> toDtoList(List<PlayerClassModel> playerClassModelList);

    List<PlayerClassModel> toModelList(List<PlayerClassDto> playerClassDtoList);

}
