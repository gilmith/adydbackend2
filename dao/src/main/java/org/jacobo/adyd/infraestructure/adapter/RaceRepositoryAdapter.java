package org.jacobo.adyd.infraestructure.adapter;


import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.infraestructure.mapper.RaceMapper;
import org.jacobo.adyd.infraestructure.persistence.RaceJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RaceRepositoryAdapter implements RaceRepository {

    private final RaceJpaRepository raceJpaRepository;
    private final RaceMapper raceMapper;
    @Override
    public RaceModel save(RaceModel campaign) {
        return raceMapper.toDomain(raceJpaRepository.save(raceMapper.toEntity(campaign)));
    }

    @Override
    public Optional<RaceModel> findById(Long id) {
        return raceJpaRepository.findById(id).map(raceMapper::toDomain);
    }

    @Override
    public List<RaceModel> findAll() {
        return raceJpaRepository.findAll().stream().map(raceMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        raceJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return raceJpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return raceJpaRepository.count();
    }
}
