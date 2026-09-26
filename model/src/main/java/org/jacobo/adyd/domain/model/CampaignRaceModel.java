package org.jacobo.adyd.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CampaignRaceModel {

    private String campaignName;
    private List<RaceModel> races;
}
