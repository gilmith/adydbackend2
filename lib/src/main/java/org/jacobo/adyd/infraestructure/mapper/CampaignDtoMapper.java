package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.CampaignDto;
import org.jacobo.adyd.api.dto.CampaignPlayerClassDto;
import org.jacobo.adyd.api.dto.CampaignRaceDto;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RaceDtoMapper.class, PlayerClassDtoMapper.class})
public interface CampaignDtoMapper {

    CampaignDto toDto(CampaignModel campaign);

    List<CampaignDto> toDto(List<CampaignModel> campaign);

    CampaignModel toModel(CampaignDto campaign);

    @Mapping(target = "updateDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    CampaignModel getModifiedCampaignModel(@MappingTarget CampaignModel target, CampaignModel source);

    @Mapping(target = "campaign", source = "campaignName")
    @Mapping(target = "races", source = "races")
    CampaignRaceDto toCampaignRaceDto(CampaignRaceModel campaignRace);

    @Mapping(target = "campaign", source = "campaign")
    @Mapping(target = "playerClass", source = "playerClass")
    CampaignPlayerClassDto toCampaignPlayerClassDto(CampaignPlayerClassModel playerClassForCampaign);
}
