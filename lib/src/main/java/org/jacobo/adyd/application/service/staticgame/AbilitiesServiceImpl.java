package org.jacobo.adyd.application.service.staticgame;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;
import org.jacobo.adyd.domain.repository.staticgame.AbilitiesCustomRepository;
import org.jacobo.adyd.domain.service.staticgame.AbilitiesService;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbilitiesServiceImpl implements AbilitiesService {

    private final AbilitiesCustomRepository abilityRepository;

    @Override
    public <T extends BaseStaticPointModel> T getAbilityByBasePointId(String basePointId, StaticAbilitiesEnum abilitiesEnum) {
        return  abilityRepository.findByBasePoint(basePointId, abilitiesEnum);
    }
}
