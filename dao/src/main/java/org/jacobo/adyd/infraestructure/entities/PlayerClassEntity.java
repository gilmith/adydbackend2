package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "campaigns")
@ToString(callSuper = true, exclude = "campaigns")
@Entity
@Table(name = "player_class")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerClassEntity extends CommonEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "hit_dice")
    private String hitDice;

    @ManyToMany(mappedBy = "playerClasses", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;
}
