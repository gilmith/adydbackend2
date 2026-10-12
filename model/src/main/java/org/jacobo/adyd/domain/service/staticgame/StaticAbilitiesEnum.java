package org.jacobo.adyd.domain.service.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.*;

public enum StaticAbilitiesEnum {

    STRENGTH(StrengthModel.class),

    DEXTERITY(DexterityModel.class),

    INTELLIGENCE(IntelligenceModel.class),

    WISDOM(WisdomModel.class),

    CONSTITUTION(ConstitutionModel.class),

    CHARISMA(CharismaModel.class),
    ;

    private final Class<?> model;

    StaticAbilitiesEnum(Class<?> model) {
        this.model = model;
    }

    public Class<?> getModel() {
        return model;
    }
}