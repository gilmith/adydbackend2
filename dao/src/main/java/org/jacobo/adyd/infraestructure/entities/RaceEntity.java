package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "race")
@Getter
@Setter
public class RaceEntity extends CommonEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "raceEntities", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;
}
