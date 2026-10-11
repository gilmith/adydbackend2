package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "abilities_strength")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StrengthEntity extends  StaticTableBasePointEntity {

    @Column(name = "hit_adjustment", insertable = false, updatable = false)
    private Integer hitAdjustment;

    @Column(name = "damage_adjustment", insertable = false, updatable = false)
    private Integer damageAdjustment;

    @Column(name = "weight_allow", insertable = false, updatable = false)
    private Double weightAllow;

    @Column(name = "max_press", insertable = false, updatable = false)
    private Double maxPress;

    @Column(name = "open_doors", insertable = false, updatable = false)
    private String openDoors;

    @Column(name = "bend_bars", insertable = false, updatable = false)
    private Integer bendBars;
}
