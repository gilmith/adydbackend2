package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;

import java.util.List;

public interface CampaignService {

    List<CampaignModel> findAll();

    CampaignModel findById(Long id);

    CampaignModel create(CampaignModel campaign);

    CampaignModel update(Long id, CampaignModel campaign);

    CampaignRaceModel findAllRacesByCampaignId(Long campaignId);

    void deleteCampaign(Long id);

    void deleteCampaignRace(Long campaignId, Long raceId);

    void addRaceInCampaign(Long campaignId, Long raceId);

    void addPlayerClassInCampaign(Long campaignId, Long playerClassId);

    CampaignPlayerClassModel getPlayerClassForCampaign(Long campaignId);
}
