package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.PlayerClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerClassJpaRepository extends JpaRepository<PlayerClassEntity, Long> {
}