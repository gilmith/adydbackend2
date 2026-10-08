package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;
import org.jacobo.adyd.domain.validator.SortValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;

public interface CampaignService {

    @SortValidator(CampaignModel.class)
    Page<CampaignModel> findAll(PageRequest pageable);

    CampaignModel findById(Long id);

    CampaignModel create(CampaignModel campaign);

    CampaignModel update(CampaignModel campaign) throws IOException;

    CampaignRaceModel findAllRacesByCampaignId(Long campaignId);

    void deleteCampaign(Long id);

    void deleteCampaignRace(Long campaignId, Long raceId);

    void addRaceInCampaign(Long campaignId, Long raceId);

    void addPlayerClassInCampaign(Long campaignId, Long playerClassId);

    CampaignPlayerClassModel getPlayerClassForCampaign(Long campaignId);

    CampaignRaceModel getRaceByCampaignId(Long campaignId);
}
