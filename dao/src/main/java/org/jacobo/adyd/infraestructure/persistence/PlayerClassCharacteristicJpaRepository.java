package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.PlayerClassCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerClassCharacteristicJpaRepository extends JpaRepository<PlayerClassCharacteristicEntity, Long> {
}
