package org.jacobo.adyd.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.jacobo.adyd.infraestructure.mapper.CampaignMapper;
import org.jacobo.adyd.infraestructure.persistence.CampaignJpaRepository;
import org.jacobo.adyd.infraestructure.projection.CampaignRaceProjection;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CampaignRepositoryAdapter implements CampaignRepository {

    private final CampaignJpaRepository jpaRepository;
    private final CampaignMapper campaignMapper;

    @Override
    @Transactional
    public CampaignModel save(CampaignModel campaign) {
        return campaignMapper.toDomain(jpaRepository.save(campaignMapper.toEntity(campaign)));
    }


    @Override
    public Optional<CampaignModel> findById(Long id) {
        return jpaRepository.findById(id).map(campaignMapper::toDomain);
    }

    @Override
    public List<CampaignModel> findAll() {
        return campaignMapper.toDomain(jpaRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public Optional<CampaignRaceModel> findAllRacesByCampaignId(Long campaignId) {
        List<CampaignRaceProjection> projections = jpaRepository.findAllRacesByCampaignId(campaignId);
        if (projections.isEmpty()) {
            return Optional.empty();
        }
        CampaignRaceModel campaignRace = new CampaignRaceModel();
        campaignRace.setCampaignName(projections.getFirst().getCampaignName());
        campaignRace.setRaces(projections.stream()
                .map(it -> {
                    val raceModel = new RaceModel();
                    raceModel.setName(it.getRaceName());
                    raceModel.setId(it.getRaceId());
                    return raceModel;
                })
                .toList()
        );
        return Optional.of(campaignRace);
    }

    @Override
    public Optional<CampaignPlayerClassModel> findPlayerClassForCampaign(Long campaignId) {
        val projection = jpaRepository.findAllPlayerClassForCampaign(campaignId);
        if (projection.isEmpty()) {
            return Optional.empty();
        }
        val campaignPlayerClassModel = new CampaignPlayerClassModel();
        campaignPlayerClassModel.setCampaign(projection.getFirst().getCampaignName());
        val playerClass = projection.stream().map(it -> {
            val playerClassModel = new PlayerClassModel();
            playerClassModel.setName(it.getPlayerClassName());
            playerClassModel.setId(it.getPlayerClassId());
            return playerClassModel;
        }).toList();
        campaignPlayerClassModel.setPlayerClass(playerClass);
        return Optional.of(campaignPlayerClassModel);
    }

}