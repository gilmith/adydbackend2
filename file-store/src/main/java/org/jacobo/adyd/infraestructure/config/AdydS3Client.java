package org.jacobo.adyd.infraestructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class AdydS3Client {

    @Value("${s3.secret}")
    private String secret;

    @Value("${s3.key}")
    private String key;

    @Value("${s3.url-upload}")
    private String endpoint;

    @Bean
    public S3Client amazonS3() throws URISyntaxException {
        return S3Client.builder()
                .endpointOverride(new URI(endpoint))
                .region(Region.AP_NORTHEAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(key, secret))
                )
                .serviceConfiguration(S3Configuration.builder()
                    .pathStyleAccessEnabled(true).build()) // OBLIGATORIO para SeaweedFS
                .build();
    }

}
