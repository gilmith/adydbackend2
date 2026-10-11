package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "abilities_constitution")
@Data
@NoArgsConstructor
public class ConstitutionEntity extends StaticTableBasePointEntity {

    @Column(name = "hit_poiint_adjustment", insertable = false, updatable = false)
    private Integer hitPointAdjustment;

    @Column(name = "system_shock", insertable = false, updatable = false)
    private Integer systemShock;

    @Column(name = "resurrect_survival", insertable = false, updatable = false)
    private Integer resurrectSurvival;

    @Column(name = "poison_save", insertable = false, updatable = false)
    private Integer poisonSave;

    @Column(name = "regen", insertable = false, updatable = false)
    private String regeneration;
}
