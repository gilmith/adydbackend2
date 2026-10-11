package org.jacobo.adyd.domain.model.staticgamevalues;


import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class StrengthModel extends BaseStaticPointModel {

    private Integer hitAdjustment;
    private Integer damageAdjustment;
    private Double weightAllow;
    private Double maxPress;
    private String openDoors;
    private Integer bendBars;

}
