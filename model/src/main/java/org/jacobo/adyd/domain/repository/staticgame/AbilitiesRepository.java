package org.jacobo.adyd.domain.repository.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;

public interface AbilitiesRepository {

    <T extends BaseStaticPointModel> T getAbilityByBasePointId(String basePointId, StaticAbilitiesEnum abilitiesEnum);

}
