package org.jacobo.adyd.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.infraestructure.mapper.PlayerClassMapper;
import org.jacobo.adyd.infraestructure.persistence.PlayerClassJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerClassRepositoryAdapter implements PlayerClassRepository {

    private final PlayerClassJpaRepository jpaRepository;
    private final PlayerClassMapper playerClassMapper;


    @Override
    @Transactional
    public PlayerClassModel save(PlayerClassModel playerClass) {
        return playerClassMapper.toDomain(jpaRepository.save(playerClassMapper.toEntity(playerClass)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlayerClassModel> findById(Long id) {
        return jpaRepository.findById(id).map(playerClassMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerClassModel> findAll() {
        return jpaRepository.findAll().stream().map(playerClassMapper::toDomain).toList();
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