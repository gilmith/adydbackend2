package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "campaign")
@Getter
@Setter
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
