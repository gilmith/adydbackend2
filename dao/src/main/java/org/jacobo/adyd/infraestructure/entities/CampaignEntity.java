package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"playerClasses", "raceEntities", "fileStoreEntity"})
@ToString(callSuper = true, exclude = {"playerClasses", "raceEntities", "fileStoreEntity"})
@Entity
@Table(name = "campaign")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CampaignEntity extends CommonEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "campaign_player_class",
            joinColumns = @JoinColumn(name = "id_campaign"),
            inverseJoinColumns = @JoinColumn(name = "id_player_class"))
    private Set<PlayerClassEntity> playerClasses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "race_campaign",
            joinColumns = @JoinColumn(name = "id_campaign"),
            inverseJoinColumns = @JoinColumn(name = "id_race"))
    private Set<RaceEntity> raceEntities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_store_id")
    private FileStoreEntity fileStoreEntity;

}
