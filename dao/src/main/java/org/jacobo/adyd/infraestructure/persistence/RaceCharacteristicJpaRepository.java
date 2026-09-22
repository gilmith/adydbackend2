package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.RaceCharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RaceCharacteristicJpaRepository extends JpaRepository<RaceCharacteristicsEntity, Long> {

    @Query("SELECT rc FROM RaceCharacteristicsEntity rc WHERE rc.race.id = :raceId")
    List<RaceCharacteristicsEntity> findAllByRaceId(Long raceId);
}
