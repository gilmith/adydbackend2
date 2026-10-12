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
public class DexterityModel extends BaseStaticPointModel {

    private Integer surpriseAdjustment;
    private Integer missileAttackAdjustment;
    private Integer defensiveAdjustment;
}
