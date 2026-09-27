package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.PlayerClassCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerClassCharacteristicJpaRepository extends JpaRepository<PlayerClassCharacteristicEntity, Long> {

    @Query("SELECT pcc FROM PlayerClassCharacteristicEntity pcc WHERE pcc.playerClass.id = :playerClassId")
    List<PlayerClassCharacteristicEntity> findAllByPlayerClassId(Long playerClassId);
}
