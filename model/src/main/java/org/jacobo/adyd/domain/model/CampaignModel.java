package org.jacobo.adyd.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CampaignModel extends BaseModel{
    private String name;
    private Set<RaceModel> races;
    private Set<PlayerClassModel> playerClasses;
}
