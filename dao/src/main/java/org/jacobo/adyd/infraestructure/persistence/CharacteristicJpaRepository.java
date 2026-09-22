package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.CharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CharacteristicJpaRepository extends JpaRepository<CharacteristicsEntity, Long> {

    Optional<CharacteristicsEntity> findByCode(String code);

    @Query("""
        SELECT C FROM CharacteristicsEntity C JOIN C.raceCharacteristicsEntity RC
            JOIN RC.race R WHERE (C.code = :code OR C.name = :code) AND R.id = :raceId
    """)
    Optional<CharacteristicsEntity> findByCodeAndRace(String code, Long raceId);
}