package org.jacobo.adyd.domain.service.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;

public interface AbilitiesService {

    <T extends BaseStaticPointModel> T getAbilityById(Long id, StaticAbilitiesEnum abilitiesEnum);

}
