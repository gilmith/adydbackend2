package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RaceMapper.class})
public interface CampaignMapper {

    @Mapping(target = "races", source = "raceEntities")
    CampaignModel toDomain(CampaignEntity campaignEntity);

    List<CampaignModel> toDomain(List<CampaignEntity> campaignEntities);

    @Mapping(target = "raceEntities", source = "races")
    CampaignEntity toEntity(CampaignModel campaign);

}