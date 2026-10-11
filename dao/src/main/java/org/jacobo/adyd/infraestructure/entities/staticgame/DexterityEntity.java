package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "abilities_dexterity")
public class DexterityEntity extends StaticTableBasePointEntity{

    @Column(name = "surprise_adjustment", insertable = false, updatable = false)
    private Integer surpriseAdjustment;

    @Column(name = "missile_attack_adjustment", insertable = false, updatable = false)
    private Integer missileAttackAdjustment;

    @Column(name = "defensive_adjustment", insertable = false, updatable = false)
    private Integer defensiveAdjustment;

}
