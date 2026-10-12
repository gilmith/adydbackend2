package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "abilities_intelligence")
public class IntelligenceEntity extends StaticTableBasePointEntity{

    @Column(name = "languages", insertable = false, updatable = false)
    private Integer languages;

    @Column(name = "spell_level", insertable = false, updatable = false)
    private Integer spellLevel;

    @Column(name = "learn_spell", insertable = false, updatable = false)
    private Integer learnSpell;

    @Column(name = "max_number_spells", insertable = false, updatable = false)
    private Integer maxNumberSpells;

    @Column(name = "illusion_immun", insertable = false, updatable = false)
    private Integer illusionImmune;
}
