package org.jacobo.adyd.domain.model.staticgamevalues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class IntelligenceModel extends BaseStaticPointModel {

    private Integer languages;
    private Integer spellLevel;
    private Integer learnSpell;
    private Integer maxNumberSpells;
    private Integer illusionImmune;
}
