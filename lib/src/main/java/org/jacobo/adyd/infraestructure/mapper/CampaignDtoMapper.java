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

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {RaceDtoMapper.class, PlayerClassDtoMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CampaignDtoMapper {

    @Mapping(target = "lastUpdate", expression = "java(resolveLastUpdate(campaign, xApiVersion))")
    @Mapping(target = "lastUpdater", expression = "java(resolveLastUpdater(campaign, xApiVersion))")
    CampaignDto toDto(CampaignModel campaign, String xApiVersion);

    default LocalDateTime resolveLastUpdate(CampaignModel campaignModel, String xApiVersion) {
        if(xApiVersion.equals("2.0")) {
            if (Objects.nonNull(campaignModel.getUpdateDate()) && campaignModel.getUpdateDate().isAfter(campaignModel.getCreateDate())) {
                return campaignModel.getUpdateDate();
            }
            return campaignModel.getCreateDate();
        }
        return null;
    }

    default String resolveLastUpdater(CampaignModel campaignModel, String xApiVersion) {
        if(xApiVersion.equals("2.0")) {
            if (Objects.nonNull(campaignModel.getUpdateDate()) && campaignModel.getUpdateDate().isAfter(campaignModel.getCreateDate())) {
                return campaignModel.getUpdateUser();
            }
            return campaignModel.getCreateUser();
        }
        return null;
    }


    @Mapping(target = "page", source = "campaign.number")
    @Mapping(target = "totalPages", source = "campaign.totalPages")
    @Mapping(target = "totalElements", source = "campaign.totalElements")
    @Mapping(target = "content", expression = "java(mapContent(campaign.getContent(), xApiVersion))")
    PaginatedDto toDto(Page<CampaignModel> campaign, String xApiVersion);

    default java.util.List<PaginatedDtoContentInner> mapContent(
            java.util.List<CampaignModel> content, String xApiVersion) {
        if (content == null) {
            return null;
        }
        return content.stream()
                .map(item -> (PaginatedDtoContentInner) toDto(item, xApiVersion))
                .toList();
    }


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
    CampaignModel getModifiedCampaignModel(@MappingTarget CampaignModel target, CampaignModel source);

    @Mapping(target = "campaign", source = "campaignName")
    @Mapping(target = "races", source = "races")
    CampaignRaceDto toCampaignRaceDto(CampaignRaceModel campaignRace);

    @Mapping(target = "campaign", source = "campaign")
    @Mapping(target = "playerClass", source = "playerClass")
    CampaignPlayerClassDto toCampaignPlayerClassDto(CampaignPlayerClassModel playerClassForCampaign);

}
