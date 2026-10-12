package org.jacobo.adyd.infraestructure.persistence.staticgame;

import lombok.Getter;
import org.jacobo.adyd.infraestructure.entities.staticgame.CharismaEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.ConstitutionEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.DexterityEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.IntelligenceEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.StrengthEntity;
import org.jacobo.adyd.infraestructure.entities.staticgame.WisdomEntity;

@Getter
public enum StaticAbilitiesEntityEnum {

    STRENGTH(StrengthEntity.class, "SELECT s.* FROM abilities_strength s WHERE s.id = :id"),

    DEXTERITY(DexterityEntity.class, "SELECT d.* FROM abilities_dexterity d WHERE d.id = :id"),

    INTELLIGENCE(IntelligenceEntity.class, "SELECT i.* FROM abilities_intelligence i WHERE i.id = :id"),

    WISDOM(WisdomEntity.class, "SELECT w.* FROM abilities_wisdom w WHERE w.id = :id"),

    CONSTITUTION(ConstitutionEntity.class, "SELECT c.* FROM abilities_constitution c WHERE c.id = :id"),

    CHARISMA(CharismaEntity.class, "SELECT c.* FROM abilities_charisma c WHERE c.id = :id");

    private final Class<?> clazz;
    private final String query;

    StaticAbilitiesEntityEnum(Class<?> clazz, String query) {
        this.clazz = clazz;
        this.query = query;
    }

}
