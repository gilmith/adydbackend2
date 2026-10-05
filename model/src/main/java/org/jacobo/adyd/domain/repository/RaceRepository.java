package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.RaceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface RaceRepository {

    RaceModel save(RaceModel campaign);

    Optional<RaceModel> findById(Long id);

    Page<RaceModel> findAll(PageRequest pagination);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}
