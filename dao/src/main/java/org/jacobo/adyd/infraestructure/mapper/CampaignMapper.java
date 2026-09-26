package org.jacobo.adyd.infraestructure.mapper;

import lombok.val;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.jacobo.adyd.infraestructure.projection.CampaignPlayerClassProjection;
import org.jacobo.adyd.infraestructure.projection.CampaignRaceProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RaceMapper.class})
public interface CampaignMapper {

    @Mapping(target = "races", source = "raceEntities")
    CampaignModel toDomain(CampaignEntity campaignEntity);

    List<CampaignModel> toDomain(List<CampaignEntity> campaignEntities);

    @Mapping(target = "raceEntities", source = "races")
    CampaignEntity toEntity(CampaignModel campaign);


    default CampaignPlayerClassModel projectionToDomain(List<CampaignPlayerClassProjection> projection){
        final Map<String, List<PlayerClassModel>> grouped = projection.stream().collect(Collectors.groupingBy(
                CampaignPlayerClassProjection::getCampaignName,
                Collectors.mapping(this::toPlayerClassModel, Collectors.toList())));
        val campaignName = grouped.keySet().iterator().next();
        return CampaignPlayerClassModel.builder()
                .campaign(campaignName)
                .playerClass(grouped.get(campaignName))
                .build();
    }

    default PlayerClassModel toPlayerClassModel(CampaignPlayerClassProjection projection){
        return PlayerClassModel.builder()
                .id(projection.getPlayerClassId())
                .name(projection.getPlayerClassName())
                .hitDice(projection.getHitDice())
                .build();
    }

    default CampaignRaceModel raceProjectionToDoman(List<CampaignRaceProjection> projections) {
        final Map<String, List<RaceModel>> grouped = projections.stream().collect(Collectors.groupingBy(
                CampaignRaceProjection::getCampaignName,
                Collectors.mapping(this::toRaceModel, Collectors.toList())
        ));
        val campaignName = grouped.keySet().iterator().next();
        return CampaignRaceModel.builder()
                .campaignName(campaignName)
                .races(grouped.get(campaignName))
                .build();
    }

    default RaceModel toRaceModel(CampaignRaceProjection campaignRaceProjection) {
        return RaceModel.builder()
                .id(campaignRaceProjection.getRaceId())
                .name(campaignRaceProjection.getRaceName())
                .build();
    }
}