package org.frompast.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.frompast.exceptions.MinioConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@Slf4j
public class MinioConfiguration {

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.bucket}")
    private String bucket;

    @Bean
    @ConditionalOnProperty(name = "minio.enabled", havingValue = "true")
    public MinioClient minioClient() {
        MinioClient client = new MinioClient.Builder()
                .credentials(accessKey, secretKey)
                .endpoint(minioUrl)
                .build();

        BucketExistsArgs existsArgs = BucketExistsArgs.builder()
                .bucket(bucket)
                .build();

        try {
            if (!client.bucketExists(existsArgs)) {
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucket).build();
                client.makeBucket(makeBucketArgs);
            }
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | IOException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            log.error("Minio configuration exception: {}", e.getMessage());
            throw new MinioConfigurationException(e);
        }
        return client;
    }

}
