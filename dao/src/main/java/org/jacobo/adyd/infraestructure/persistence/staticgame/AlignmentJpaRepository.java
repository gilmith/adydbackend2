package org.jacobo.adyd.infraestructure.persistence.staticgame;

import org.jacobo.adyd.infraestructure.entities.staticgame.AlignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlignmentJpaRepository extends JpaRepository<AlignmentEntity, Long> {

}
