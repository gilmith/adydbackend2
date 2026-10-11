package org.jacobo.adyd.infraestructure.persistence.staticgame;

import org.jacobo.adyd.infraestructure.entities.staticgame.CharismaEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.ConstitutionEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.DexterityEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.IntelligenceEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.StrengthEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.WisdomEntity;

public enum StaticAbilitiesEntityEnum {

    STRENGTH(StrengthEntity.class, "SELECT s.* FROM abilities_strength s WHERE s.base_Point = :basePoint"),

    DEXTERITY(DexterityEntity.class, "SELECT d.* FROM abilities_dexterity d WHERE d.base_Point = :basePoint"),

    INTELLIGENCE(IntelligenceEntity.class, "SELECT i.* FROM abilities_intelligence i WHERE i.base_Point = :basePoint"),

    WISDOM(WisdomEntity.class, "SELECT w.* FROM abilities_wisdom w WHERE w.base_Point = :basePoint"),

    CONSTITUTION(ConstitutionEntity.class, "SELECT c.* FROM abilities_constitution c WHERE c.base_Point = :basePoint"),

    CHARISMA(CharismaEntity.class, "SELECT c.* FROM abilities_charisma c WHERE c.base_Point = :basePoint");

    private final Class<?> clazz;
    private final String query;

    StaticAbilitiesEntityEnum(Class<?> clazz, String query) {
        this.clazz = clazz;
        this.query = query;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getQuery() {
        return query;
    }
}
