package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.RaceCharacteristicModel;
import org.jacobo.adyd.domain.model.RaceModel;
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
//                .orElseThrow(() -> new RuntimeException("Race characteristic not found for race " + raceId));
    }
}