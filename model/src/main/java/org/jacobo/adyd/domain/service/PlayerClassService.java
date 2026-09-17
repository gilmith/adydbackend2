package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.PlayerClassModel;

import java.util.List;

public interface PlayerClassService {

    List<PlayerClassModel> getAll();

    PlayerClassModel getById(Long id);

    void deleteById(Long id);

    PlayerClassModel createNewPlayerClass(PlayerClassModel playerClassModel);

    PlayerClassModel update(PlayerClassModel playerClassModel);

}
