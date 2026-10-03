package org.jacobo.adyd.infraestructure.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jacobo.adyd.domain.service.FileStoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class FileStoreServiceImpl implements FileStoreService {

    private final S3Client adydS3;
    private final String fileStoreUrl;

    public FileStoreServiceImpl(S3Client adydS3, @Value("${s3.url-download}") String fileStoreUrl) {
        this.adydS3 = adydS3;
        this.fileStoreUrl = fileStoreUrl;
    }

    @Override
    public String uploadFile(String fileName, String bucket, String contentType, InputStream inputStream) throws IOException {
        val request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .contentType(contentType)
                .build();
        adydS3.putObject(request, RequestBody.fromBytes(inputStream.readAllBytes()));

        return String.format("%s/buckets/%s/%s", fileStoreUrl, bucket, fileName);
    }

    @Override
    public Boolean exists(String fileName, String bucket) {
        val headRequest = HeadObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
        try {
            return adydS3.headObject(headRequest).sdkHttpResponse().isSuccessful();
        } catch (NoSuchKeyException e) {
            log.info("File does not exist in S3 bucket");
            return false;
        }
    }


}
