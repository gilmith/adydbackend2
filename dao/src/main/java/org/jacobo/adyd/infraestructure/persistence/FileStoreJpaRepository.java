package org.jacobo.adyd.infraestructure.persistence;

import org.jacobo.adyd.infraestructure.entities.FileStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileStoreJpaRepository extends JpaRepository<FileStoreEntity, Long> {

    Optional<FileStoreEntity> findByFileName(String fileName);
}
