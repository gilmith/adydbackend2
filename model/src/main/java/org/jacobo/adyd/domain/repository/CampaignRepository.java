package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.CampaignModel;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {

    CampaignModel save(CampaignModel campaign);

    Optional<CampaignModel> findById(Long id);

    List<CampaignModel> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}