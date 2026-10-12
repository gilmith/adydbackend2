package org.jacobo.adyd.domain.repository.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;


public interface AbilitiesCustomRepository {

    <T extends BaseStaticPointModel> T findByBasePoint(Long id, StaticAbilitiesEnum abilitiesEnum);



}
