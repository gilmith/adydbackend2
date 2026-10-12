package org.jacobo.adyd.infraestructure.entities.staticgame;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "abilities_wisdom")
public class WisdomEntity extends StaticTableBasePointEntity{

    @Column(name = "magical_defense_adjustment", insertable = false, updatable = false)
    private Integer magicalDefenseAdjustment;

    @Column(name = "bonus_spells", insertable = false, updatable = false)
    private Integer bonusSpells;

    @Column(name = "spell_failure", insertable = false, updatable = false)
    private Integer spellFailure;
}
