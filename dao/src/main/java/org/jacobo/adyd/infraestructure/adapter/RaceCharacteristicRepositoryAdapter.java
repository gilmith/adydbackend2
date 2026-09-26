package org.jacobo.adyd.infraestructure.adapter;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.repository.RaceCharacteristicRepository;
import org.jacobo.adyd.infraestructure.entities.RaceCharacteristicsEntity;
import org.jacobo.adyd.infraestructure.mapper.CharacteristicMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceCharacteristicMapper;
import org.jacobo.adyd.infraestructure.mapper.RaceMapper;
import org.jacobo.adyd.infraestructure.persistence.RaceCharacteristicJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceCharacteristicRepositoryAdapter implements RaceCharacteristicRepository {

    private final RaceCharacteristicJpaRepository raceCharacteristicJpaRepository;
    private final RaceCharacteristicMapper raceCharacteristicMapper;
    private final CharacteristicMapper characteristicMapper;
    private final RaceMapper raceMapper;

    @Override
    @Transactional
    public RaceCharacteristicModel save(RaceCharacteristicModel raceCharacteristicModel) {
        return raceCharacteristicMapper.toRaceModel(
                raceCharacteristicJpaRepository.save(raceCharacteristicMapper.toEntity(raceCharacteristicModel)));
    }

    @Override
    @Transactional(readOnly = true)
    public RaceCharacteristicModel findByRaceId(Long raceId) {
        val listRaceCharacteristic = raceCharacteristicJpaRepository.findAllByRaceId(raceId);
        if (listRaceCharacteristic.isEmpty()) {
            throw new NotFoundRunTimeException("Race characteristic not found");
        }
        val raceEntityListMap = listRaceCharacteristic.stream().collect(Collectors.groupingBy(
                RaceCharacteristicsEntity::getRace,
                Collectors.toList()
        ));
        return raceCharacteristicMapper.mapToModel(raceEntityListMap);
    }

    @Override
    public RaceCharacteristicModel findByRaceIdAndCharacteristicId(Long raceId, Long characteristicId) {
        return raceCharacteristicMapper.toRaceModel(
                raceCharacteristicJpaRepository.findByRaceIdAndCharacteristicId(raceId, characteristicId));
    }
}