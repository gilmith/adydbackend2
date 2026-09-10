package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.RaceModel;

import java.util.List;
import java.util.Optional;

public interface RaceRepository {

    RaceModel save(RaceModel campaign);

    Optional<RaceModel> findById(Long id);

    List<RaceModel> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}
