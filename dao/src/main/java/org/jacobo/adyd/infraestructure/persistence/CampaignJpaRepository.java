package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.jacobo.adyd.infraestructure.projection.CampaignPlayerClassProjection;
import org.jacobo.adyd.infraestructure.projection.CampaignRaceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CampaignJpaRepository extends JpaRepository<CampaignEntity, Long> {

    @Query("""
        SELECT c.name as campaignName, r.name as raceName, r.id as raceId FROM CampaignEntity c JOIN c.raceEntities r WHERE c.id = :campaignId
    """)
    List<CampaignRaceProjection> findAllRacesByCampaignId(Long campaignId);

    void deleteCampaignEntitiesByRaceEntities(Set<RaceEntity> raceEntities);

    @Query("""
        SELECT c.name as campaignName, p.name as playerClassName, p.id as playerClassId, p.hitDice as hitDice FROM CampaignEntity c JOIN c.playerClasses p WHERE c.id = :campaignId
    """)
    List<CampaignPlayerClassProjection> findAllPlayerClassForCampaign(Long campaignId);
}