package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.model.FileStoreModel;

import java.io.InputStream;

public interface FileService {

    Boolean existsFileInStorage(String fileName, String bucket);
    FileStoreModel uploadAndGenerateUrl(String fileName, InputStream stream);
    FileStoreModel getFileByName(String fileName);
}
