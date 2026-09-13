package org.jacobo.adyd.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.CampaignModel;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.infraestructure.mapper.CampaignMapper;
import org.jacobo.adyd.infraestructure.persistence.CampaignJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public Optional<CampaignModel> findById(Long id) {
        return jpaRepository.findById(id).map(campaignMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampaignModel> findAll() {
        return jpaRepository.findAll().stream().map(campaignMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return jpaRepository.count();
    }
}