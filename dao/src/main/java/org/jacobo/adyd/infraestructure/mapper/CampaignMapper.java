package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignModel toDomain(CampaignEntity campaignEntity);

    List<CampaignModel> toDomain(List<CampaignEntity> campaignEntities);

    CampaignEntity toEntity(CampaignModel campaign);

}