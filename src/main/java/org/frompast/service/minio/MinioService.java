package org.frompast.service.minio;

import io.minio.GetObjectResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@ConditionalOnProperty(name = "minio.enabled", havingValue = "true")
public interface MinioService {

    GetObjectResponse getFile(String name);
    GetObjectResponse getFile(String userId, String name);

    void putFile(ByteArrayOutputStream stream, String filePath);
    void putFile(String userId, ByteArrayOutputStream stream, String filePath);

    String uploadFileAndGetFileUrl(InputStream inputStream, String fileName);
    String uploadFileAndGetFileUrl(UUID userId, InputStream inputStream, String fileName);

    void deleteFile(String fileName);
    void deleteFile(String userId, String fileName);
    List<String> scanForFiles(String path);

    byte[] getFileByBucket(String bucketName, String objectName);
}
