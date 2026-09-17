package org.jacobo.adyd.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampaignPlayerClassModel {
    private String campaign;
    private List<PlayerClassModel> playerClass;
}
