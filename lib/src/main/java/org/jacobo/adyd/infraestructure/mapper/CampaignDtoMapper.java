package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.*;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RaceDtoMapper.class, PlayerClassDtoMapper.class})
public interface CampaignDtoMapper {

    @Mapping(target = "fileStoreUrl", source = "fileStore.url")
    @Mapping(target = "description", source = "campaignDescription")
    CampaignDto toDto(CampaignModel campaign);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "content", source = "content")
    PaginatedDto toDto(Page<CampaignModel> campaign);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileStore", ignore = true)
    CampaignModel fromCreateModel(CreateCampaignDto campaignDto);

    @Mapping(target = "campaignDescription", source = "description")
    CampaignModel toModel(CampaignDto campaign);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "races", ignore = true)
    @Mapping(target = "playerClasses", ignore = true)
    @Mapping(target = "name", conditionExpression = "java(source.getName() == null)", ignore = true)
    @NullValuePropertyMappingStrategy(NullValuePropertyMappingStrategy.IGNORE)
    CampaignModel getModifiedCampaignModel(@MappingTarget CampaignModel target, CampaignModel source);

    @Mapping(target = "campaign", source = "campaignName")
    @Mapping(target = "races", source = "races")
    CampaignRaceDto toCampaignRaceDto(CampaignRaceModel campaignRace);

    @Mapping(target = "campaign", source = "campaign")
    @Mapping(target = "playerClass", source = "playerClass")
    CampaignPlayerClassDto toCampaignPlayerClassDto(CampaignPlayerClassModel playerClassForCampaign);

}
