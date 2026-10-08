package org.jacobo.adyd.infraestructure.mapper;


import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.api.dto.PaginatedDtoContentInner;
import org.jacobo.adyd.api.dto.PlayerClassDto;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.model.RaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CampaignDtoMapper.class})
public interface PlayerClassDtoMapper {

    PlayerClassModel toModel(PlayerClassDto playerClassDto);

    PlayerClassDto toDto(PlayerClassModel playerClassModel);


    List<PlayerClassModel> toModelList(List<PlayerClassDto> playerClassDtoList);

    @Mapping(target = "fileStoreUrl", ignore = true)
    @Mapping(target = "hitDice", source = "hitDice")
    PaginatedDtoContentInner toContentInner(PlayerClassModel campaign);


    @Mapping(target = "page", source = "number")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "content", source = "content")
    PaginatedDto toDto(Page<PlayerClassModel> playerClassModels);

}
