package org.frompast.exceptions;

import org.springframework.http.HttpStatus;

public class MinioOperationException extends MinioCustomExceptions {
    /**
     * @param message Сообщение об исключении
     */
    private MinioOperationException(String message) {
        super(String.format("%s, %s", HttpStatus.INTERNAL_SERVER_ERROR, message));
    }

    public static MinioOperationException onPutFile(Throwable cause) {
        return new MinioOperationException("PUT file exception, reason: " + cause.getMessage());
    }

    public static MinioOperationException onGetFileUrl(Throwable cause) {
        return new MinioOperationException("Get file URL exception, reason: " + cause.getMessage());
    }

    public static MinioOperationException onUploadFile(Throwable cause) {
        return new MinioOperationException("Upload file exception, reason: " + cause.getMessage());
    }

    public static MinioOperationException onDeleteFile(Throwable cause) {
        return new MinioOperationException("Delete file exception, reason: " + cause.getMessage());
    }
}
