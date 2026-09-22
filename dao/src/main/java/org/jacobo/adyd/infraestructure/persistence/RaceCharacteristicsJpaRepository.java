package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.CharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceCharacteristicsJpaRepository extends JpaRepository<CharacteristicsEntity, Long> {


}
