package org.jacobo.adyd.domain.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileStoreService {

    String uploadFile(String fileName, String bucket, String contentType, InputStream inputStream) throws IOException;

    Boolean exists(String fileName, String bucket);
}
