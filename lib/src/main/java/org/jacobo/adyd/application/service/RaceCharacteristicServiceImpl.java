package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.repository.RaceCharacteristicRepository;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCharacteristicServiceImpl implements RaceCharacteristicService {

    private final RaceCharacteristicRepository raceCharacteristicRepository;

    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public RaceCharacteristicModel save(RaceCharacteristicModel characteristicModel) {
        return raceCharacteristicRepository.save(characteristicModel);
    }


    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public RaceCharacteristicModel getRaceById(Long raceId){
        return raceCharacteristicRepository.findByRaceId(raceId);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin', 'master', 'player')")
    public RaceCharacteristicModel findRaceCharacteristicByRaceIdAndCharacteristicId(Long raceId, Long characteristicId) {
        return raceCharacteristicRepository.findByRaceIdAndCharacteristicId(raceId, characteristicId);
    }
}