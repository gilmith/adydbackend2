package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "campaigns")
@ToString(callSuper = true, exclude = "campaigns")
@Entity
@Table(name = "race")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RaceEntity extends CommonEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "raceEntities", fetch = FetchType.LAZY)
    private Set<CampaignEntity> campaigns;
}
