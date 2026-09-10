package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.PlayerClassModel;

import java.util.List;
import java.util.Optional;

public interface PlayerClassRepository {

    PlayerClassModel save(PlayerClassModel playerClass);

    Optional<PlayerClassModel> findById(Long id);

    List<PlayerClassModel> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}