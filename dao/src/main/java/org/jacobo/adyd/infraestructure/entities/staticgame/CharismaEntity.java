package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "abilities_charisma")
public class CharismaEntity extends StaticTableBasePointEntity{

    @Column(name = "max_followers", insertable = false, updatable = false)
    private Integer maxFollowers;

    @Column(name = "loyalty", insertable = false, updatable = false)
    private Integer loyalty;

    @Column(name = "reaction_adjustment", insertable = false, updatable = false)
    private Integer reactionAdjustment;
}
