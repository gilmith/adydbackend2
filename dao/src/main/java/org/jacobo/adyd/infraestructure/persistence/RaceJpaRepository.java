package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceJpaRepository extends JpaRepository<RaceEntity, Long> {
}
