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
public class WisdomModel extends BaseStaticPointModel {

    private Integer magicalDefenseAdjustment;
    private Integer bonusSpells;
    private Integer spellFailure;
}
