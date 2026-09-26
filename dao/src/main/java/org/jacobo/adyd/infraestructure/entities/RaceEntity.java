package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"campaigns", "raceCharacteristicEntities"})
@ToString(callSuper = true, exclude = {"campaigns", "raceCharacteristicEntities"})
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race")
    private Set<RaceCharacteristicsEntity> raceCharacteristicEntities;

}
