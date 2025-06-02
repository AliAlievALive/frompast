package org.frompast.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.frompast.exceptions.MinioOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MinioHelper {
    private static final int UNKNOWN_OBJECT_SIZE_FLAG = -1;
    private static final long MINIMAL_VALID_PART_SIZE = FileUtils.ONE_MB * 5;
    private static final String APPLICATION_XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private final MinioClient minioClient;
    @Value("${minio.bucket}")
    private String bucket;
    @Value("${minio.url-expiry-hours}")
    private int urlExpiryHours;

    @Autowired
    public MinioHelper(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String getUserSpecificPath(String userId, String fileName) {
        return userId + "/" + fileName;
    }

    private String getUrl(String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(urlExpiryHours, TimeUnit.HOURS)
                            .build()
            );
        } catch (Exception cause) {
            throw MinioOperationException.onGetFileUrl(cause);
        }
    }

    public void deleteFromMinio(String fileName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .build();

            minioClient.removeObject(removeObjectArgs);
        } catch (Exception cause) {
            log.error("Minio delete file error: ", cause);
            throw MinioOperationException.onDeleteFile(cause);
        }
    }

    public String uploadFileAndGetUrl(InputStream inputStream, String fileName) {
        try {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(inputStream, UNKNOWN_OBJECT_SIZE_FLAG, MINIMAL_VALID_PART_SIZE)
                    .build());
            return getUrl(fileName);
        } catch (Exception cause) {
            throw MinioOperationException.onGetFileUrl(cause);
        }
    }

    public void putToMinio(String filePath, InputStream inputStream) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket)
                .object(filePath)
                .contentType(MediaType.valueOf(APPLICATION_XLSX_CONTENT_TYPE).toString())
                .stream(inputStream, UNKNOWN_OBJECT_SIZE_FLAG, MINIMAL_VALID_PART_SIZE)
                .build();

        try {
            minioClient.putObject(args);
        } catch (Exception cause) {
            throw MinioOperationException.onPutFile(cause);
        }
    }

    public GetObjectResponse uploadFile(GetObjectArgs objectArgs) {
        try {
            return minioClient.getObject(objectArgs);
        } catch (Exception cause) {
            log.error("Minio get report file error: " + cause);
            throw MinioOperationException.onUploadFile(cause);
        }
    }

    @SneakyThrows
    public List<String> scanForFiles(String path) {
        List<String> result = new ArrayList<>();
        for (Result<Item> itemResult : minioClient
                .listObjects(ListObjectsArgs.builder().bucket(bucket).prefix(path).build())) {
            result.add(itemResult.get().objectName());
        }
        return result;
    }
}
