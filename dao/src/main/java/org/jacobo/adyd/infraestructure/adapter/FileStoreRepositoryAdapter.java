package org.jacobo.adyd.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.model.FileStoreModel;
import org.jacobo.adyd.domain.repository.FileStoreRepository;
import org.jacobo.adyd.infraestructure.mapper.FileStoreMapper;
import org.jacobo.adyd.infraestructure.persistence.FileStoreJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileStoreRepositoryAdapter implements FileStoreRepository {

    private final FileStoreJpaRepository fileStoreJpaRepository;
    private final FileStoreMapper fileStoreMapper;


    @Override
    public FileStoreModel save(FileStoreModel fileStoreEntity) {
        return fileStoreMapper.toModel(fileStoreJpaRepository.save(fileStoreMapper.toEntity(fileStoreEntity)));
    }

    @Override
    public FileStoreModel findById(Long id) {
        return fileStoreMapper.toModel(fileStoreJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("File Id not found")));
    }

    @Override
    public FileStoreModel findByFileName(String fileName) {
        return fileStoreMapper.toModel(fileStoreJpaRepository.findByFileName(fileName).orElseThrow(() -> new RuntimeException("File Name not found")));
    }
}
