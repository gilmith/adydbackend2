package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.repository.RaceCharacteristicRepository;
import org.jacobo.adyd.domain.service.RaceCharacteristicService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCharacteristicServiceImpl implements RaceCharacteristicService {

    private final RaceCharacteristicRepository raceCharacteristicRepository;

    @Override
    public RaceCharacteristicModel save(RaceCharacteristicModel characteristicModel) {
        return raceCharacteristicRepository.save(characteristicModel);
    }


    @Override
    public RaceCharacteristicModel getRaceById(Long raceId){
        return raceCharacteristicRepository.findByRaceId(raceId);
    }

    @Override
    public RaceCharacteristicModel findRaceCharacteristicByRaceIdAndCharacteristicId(Long raceId, Long characteristicId) {
        return raceCharacteristicRepository.findByRaceIdAndCharacteristicId(raceId, characteristicId);
    }
}