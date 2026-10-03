package org.jacobo.adyd.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jacobo.adyd.domain.exception.AdydException;
import org.jacobo.adyd.domain.model.FileStoreModel;
import org.jacobo.adyd.domain.repository.FileStoreRepository;
import org.jacobo.adyd.domain.service.FileService;
import org.jacobo.adyd.domain.service.FileStoreService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileStoreRepository fileStoreRepository;
    private final FileStoreService fileStoreService;


    @Override
    public Boolean existsFileInStorage(String fileName, String bucket) {
        return fileStoreService.exists(fileName, bucket);
    }

    @Override
    public FileStoreModel uploadAndGenerateUrl(String fileName, InputStream stream) {
        return fileStoreRepository.findByFileName(fileName)
                .map(fileStoreModel ->
                    updateFileStoreModel(fileStoreModel, fileName, stream)
                ).orElseGet(() -> createNewFileStoreModel(fileName, stream));

    }

    private FileStoreModel createNewFileStoreModel(String fileName, InputStream stream) {
        val fileStoreModel = new FileStoreModel();
        try {
            fileStoreModel.setFileName(fileName);
            String contentType = URLConnection.guessContentTypeFromName(fileName);
            fileStoreModel.setUrl(fileStoreService.uploadFile(fileName, "campaigns", contentType,
                    stream));
            return fileStoreRepository.save(fileStoreModel);
        } catch (IOException e) {
            log.error("Error uploading campaign image", e);
            throw new AdydException("Error uploading campaign image");
        }
    }


    private FileStoreModel updateFileStoreModel(FileStoreModel fileStoreModel, String fileName, InputStream stream) {
        try (InputStream inputStream = stream) {
            String contentType = URLConnection.guessContentTypeFromName(fileName);
            fileStoreModel.setUrl(fileStoreService.uploadFile(fileName, "campaigns", contentType,
                    inputStream));
            fileStoreModel.setUpdateDate(LocalDateTime.now());
            fileStoreModel.setUpdateUser("SYSTEM");
            return fileStoreRepository.save(fileStoreModel);
        } catch (IOException e) {
            log.error("Error uploading campaign image", e);
            throw new AdydException("Error uploading campaign image");
        }
    }

    @Override
    public FileStoreModel getFileByName(String fileName) {
        return null;
    }
}
