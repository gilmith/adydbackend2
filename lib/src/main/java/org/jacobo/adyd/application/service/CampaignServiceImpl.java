package org.jacobo.adyd.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.domain.service.CampaignService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.jacobo.adyd.infraestructure.mapper.CampaignMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignDtoMapper campaignMapper;

    @Override
    public List<CampaignModel> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    public CampaignModel findById(Long id) {
        return campaignRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
    }

    @Override
    public CampaignModel create(CampaignModel campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public CampaignModel update(Long id, CampaignModel campaign) {
        val currentCampaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
        if(!campaign.getName().equals(currentCampaign.getName())) {
            return campaignRepository.save(campaignMapper.getModifiedCampaignModel(currentCampaign, campaign));
        }
        return currentCampaign;
    }
}
