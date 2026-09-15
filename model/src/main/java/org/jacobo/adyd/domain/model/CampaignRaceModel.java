package org.jacobo.adyd.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CampaignRaceModel {

    private String campaignName;
    private List<RaceModel> races;
}
