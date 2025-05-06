package org.frompast.exceptions;

import java.util.UUID;

public class CustomException extends RuntimeException {
    private static final String NOT_FOUND_BY_UUID = "%s not found by uuid: %s";

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomException(Class<?> clazz, UUID uuid) {
        super(String.format(NOT_FOUND_BY_UUID, clazz.getSimpleName(), uuid));
    }

}