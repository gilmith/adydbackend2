package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignModel toDomain(CampaignEntity campaignEntity);

    CampaignEntity toEntity(CampaignModel campaign);
}