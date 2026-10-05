package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface PlayerClassRepository {

    PlayerClassModel save(PlayerClassModel playerClass);

    Optional<PlayerClassModel> findById(Long id);

    Page<PlayerClassModel> findAll(PageRequest pagination);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}