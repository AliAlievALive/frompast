package org.frompast.service.minio.impl;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.frompast.exceptions.MinioCustomExceptions;
import org.frompast.service.minio.MinioService;
import org.frompast.utils.MinioHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@ConditionalOnProperty(name = "minio.enabled", havingValue = "true")
@RequiredArgsConstructor
@Service
public class MinioServiceImpl implements MinioService {
    private final MinioHelper minioHelper;
    private final MinioClient minioClient;
    @Value("${minio.bucket}")
    private String bucket;

    @Override
    public GetObjectResponse getFile(String name) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucket).object(name).build();
        return minioHelper.uploadFile(objectArgs);
    }

    @Override
    public GetObjectResponse getFile(String userId, String name) {
        String path = minioHelper.getUserSpecificPath(userId, name);
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucket).object(path).build();
        return minioHelper.uploadFile(objectArgs);
    }

    @Override
    public void putFile(ByteArrayOutputStream stream, String filePath) {
        InputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
        minioHelper.putToMinio(filePath, inputStream);
    }

    @Override
    public void putFile(String userId, ByteArrayOutputStream stream, String filePath) {
        String path = minioHelper.getUserSpecificPath(userId, filePath);
        InputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
        minioHelper.putToMinio(path, inputStream);
    }

    @Override
    public String uploadFileAndGetFileUrl(InputStream inputStream, String fileName) {
        return minioHelper.uploadFileAndGetUrl(inputStream, fileName);
    }

    @Override
    public String uploadFileAndGetFileUrl(UUID userId, InputStream inputStream, String fileName) {
        String path = minioHelper.getUserSpecificPath(userId.toString(), fileName);
        return minioHelper.uploadFileAndGetUrl(inputStream, path);
    }

    @Override
    public void deleteFile(String fileName) {
        minioHelper.deleteFromMinio(fileName);
    }

    @Override
    public void deleteFile(String userId, String fileName) {
        String path = minioHelper.getUserSpecificPath(userId, fileName);
        minioHelper.deleteFromMinio(path);
    }

    @Override
    public List<String> scanForFiles(String path) {
        return minioHelper.scanForFiles(path);
    }

    @Override
    public byte[] getFileByBucket(String bucketName, String objectName) {
        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        )) {
            return inputStream.readAllBytes();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new MinioCustomExceptions(e.getMessage());
        }
    }


}
