package org.jacobo.adyd.infraestructure.adapter.staticgame;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;
import org.jacobo.adyd.domain.repository.staticgame.AbilitiesRepository;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.jacobo.adyd.domain.repository.staticgame.AbilitiesCustomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbilitiesRepositoryAdapter implements AbilitiesRepository {

    private final AbilitiesCustomRepository abilitiesCustomRepository;

    @Override
    public <T extends BaseStaticPointModel> T getAbilityByBasePointId(String basePointId, StaticAbilitiesEnum abilitiesEnum) {
        return null;
    }
}
