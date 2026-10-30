package org.jacobo.adyd.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.model.*;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.infraestructure.entities.CampaignEntity;
import org.jacobo.adyd.infraestructure.entities.FileStoreEntity;
import org.jacobo.adyd.infraestructure.mapper.CampaignMapper;
import org.jacobo.adyd.infraestructure.persistence.CampaignJpaRepository;
import org.jacobo.adyd.infraestructure.persistence.FileStoreJpaRepository;
import org.jacobo.adyd.infraestructure.projection.CampaignRaceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CampaignRepositoryAdapter implements CampaignRepository {

    private final CampaignJpaRepository jpaRepository;
    private final CampaignMapper campaignMapper;
    private final FileStoreJpaRepository fileStoreJpaRepository;

    @Override
    @Transactional
    public CampaignModel save(CampaignModel campaign) {
        val entity = campaignMapper.toEntity(campaign);
        if (campaign.getFileStore() != null && campaign.getFileStore().getId() != null) {
            Optional<FileStoreEntity> existingFileStore = fileStoreJpaRepository.findById(campaign.getFileStore().getId());
            existingFileStore.ifPresent(entity::setFileStoreEntity);
        }
        return campaignMapper.toDomain(jpaRepository.save(entity));
    }


    @Override
    public Optional<CampaignModel> findById(Long id) {
        return jpaRepository.findById(id).map(campaignMapper::toDomain);
    }

    @Override
    public Page<CampaignModel> findAll(PageRequest pageable) {
        return campaignMapper.toDomain(jpaRepository.findAll(pageable));
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
        return Optional.of(campaignMapper.raceProjectionToDoman(projections));
    }

    @Override
    public Optional<CampaignPlayerClassModel> findPlayerClassForCampaign(Long campaignId) {
        val projection = jpaRepository.findAllPlayerClassForCampaign(campaignId);
        if (projection.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(campaignMapper.projectionToDomain(projection));
    }

}