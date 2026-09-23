package org.jacobo.adyd.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.model.CampaignPlayerClassModel;
import org.jacobo.adyd.domain.model.CampaignRaceModel;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.domain.service.CampaignService;
import org.jacobo.adyd.infraestructure.mapper.CampaignDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignDtoMapper campaignMapper;
    private final RaceRepository raceRepository;
    private final PlayerClassRepository playerClassRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CampaignModel> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CampaignModel findById(Long id) {
        return campaignRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
    }

    @Override
    @Transactional
    public CampaignModel create(CampaignModel campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    @Transactional
    public CampaignModel update(Long id, CampaignModel campaign) {
        val currentCampaign = campaignRepository.findById(id).orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
        if(!campaign.getName().equals(currentCampaign.getName())) {
            return campaignRepository.save(campaignMapper.getModifiedCampaignModel(currentCampaign, campaign));
        }
        return currentCampaign;
    }

    @Override
    public CampaignRaceModel findAllRacesByCampaignId(Long campaignId) {
        return campaignRepository.findAllRacesByCampaignId(campaignId)
                .orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
    }

    @Override
    @Transactional
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCampaignRace(Long campaignId, Long raceId) {
        val campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
        campaign.getRaces().removeIf(r -> r.getId().equals(raceId));
        campaignRepository.save(campaign);
    }

    @Override
    public void addRaceInCampaign(Long campaignId, Long raceId) {
        val campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
        val races = campaign.getRaces();
        val race =  raceRepository.findById(raceId).orElseThrow(() -> new NotFoundRunTimeException("Race not found"));
        races.add(race);
        campaign.setRaces(races);
        campaignRepository.save(campaign);
    }

    @Override
    public void addPlayerClassInCampaign(Long campaignId, Long playerClassId) {
        val campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
        val playerClasses = campaign.getPlayerClasses();
        val playerClass =  playerClassRepository.findById(playerClassId).orElseThrow(() -> new NotFoundRunTimeException("Race not found"));
        playerClasses.add(playerClass);
        campaign.setPlayerClasses(playerClasses);
        campaignRepository.save(campaign);
    }

    @Override
    public CampaignPlayerClassModel getPlayerClassForCampaign(Long campaignId) {
        return campaignRepository.findPlayerClassForCampaign(campaignId)
                .orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
    }

    @Override
    public CampaignRaceModel getRaceByCampaignId(Long campaignId) {
        return campaignRepository.findAllRacesByCampaignId(campaignId)
                .orElseThrow(() -> new NotFoundRunTimeException("Campaign not found"));
    }
}
