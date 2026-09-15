package org.jacobo.adyd.infraestructure.projection;

import org.jacobo.adyd.infraestructure.entities.RaceEntity;

import java.util.List;

public interface CampaignRaceProjection {

    String getCampaignName();
    String getRaceName();
    Long getRaceId();
}
