package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "player_class")
@Getter
@Setter
public class PlayerClassEntity extends CommonEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "dice_hit")
    private String diceHit;

    @ManyToMany(mappedBy = "playerClasses", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;
}
