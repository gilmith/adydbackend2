package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.CampaignModel;

import java.util.List;

public interface CampaignService {

    List<CampaignModel> findAll();

    CampaignModel findById(Long id);

    CampaignModel create(CampaignModel campaign);

    CampaignModel update(Long id, CampaignModel campaign);

}
