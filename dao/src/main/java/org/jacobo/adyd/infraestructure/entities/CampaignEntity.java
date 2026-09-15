package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "campaign")
@Data
public class CampaignEntity extends CommonEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "campaign_player_class",
            joinColumns = @JoinColumn(name = "id_campaign"),
            inverseJoinColumns = @JoinColumn(name = "id_player_class"))
    private Set<PlayerClassEntity> playerClasses;

    @ManyToMany
    @JoinTable(
            name = "race_campaign",
            joinColumns = @JoinColumn(name = "id_campaign"),
            inverseJoinColumns = @JoinColumn(name = "id_race"))
    private Set<RaceEntity> raceEntities;

}
