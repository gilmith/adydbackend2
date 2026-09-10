package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "race")
public class RaceEntity extends CommonEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "raceEntities", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;
}
