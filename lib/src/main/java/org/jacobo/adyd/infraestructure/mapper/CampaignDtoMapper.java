package org.jacobo.adyd.infraestructure.mapper;
import org.jacobo.adyd.api.dto.CampaignDto;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignDtoMapper {

    CampaignDto toDto(CampaignModel campaign);

    List<CampaignDto> toDto(List<CampaignModel> campaign);

    CampaignModel toModel(CampaignDto campaign);

    @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    CampaignModel getModifiedCampaignModel(@MappingTarget CampaignModel target, CampaignModel source);
}
