package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {

    CampaignModel save(CampaignModel campaign);

    Optional<CampaignModel> findById(Long id);

    Page<CampaignModel> findAll(PageRequest pageable);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();

    Optional<CampaignRaceModel> findAllRacesByCampaignId(Long campaignId);

    Optional<CampaignPlayerClassModel> findPlayerClassForCampaign(Long campaignId);
}