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
public class ConstitutionModel extends BaseStaticPointModel {

    private Integer hitPointAdjustment;
    private Integer systemShock;
    private Integer resurrectSurvival;
    private Integer poisonSave;
    private String regeneration;
}
