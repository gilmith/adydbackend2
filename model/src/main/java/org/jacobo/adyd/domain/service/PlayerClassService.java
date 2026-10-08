package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.domain.validator.SortValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PlayerClassService {

    @SortValidator(PlayerClassModel.class)
    Page<PlayerClassModel> getAll(PageRequest pagination);

    PlayerClassModel getById(Long id);

    void deleteById(Long id);

    PlayerClassModel createNewPlayerClass(PlayerClassModel playerClassModel);

    PlayerClassModel update(PlayerClassModel playerClassModel);

    PlayerClassModel getPlayerClassById(Long playerClassId);
}
