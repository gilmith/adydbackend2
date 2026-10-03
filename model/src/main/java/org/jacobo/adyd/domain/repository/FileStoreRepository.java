package org.jacobo.adyd.domain.repository;

import org.jacobo.adyd.domain.model.FileStoreModel;

import java.util.Optional;

public interface FileStoreRepository {

    FileStoreModel save(FileStoreModel fileStoreEntity);

    FileStoreModel findById(Long id);

    Optional<FileStoreModel> findByFileName(String fileName);

}
