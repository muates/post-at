package com.muates.postservice.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignClientExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientExceptionHandler.class);

    public <T> T handleFeignException(FeignException e, Class<T> returnType) {
        HttpStatus status = HttpStatus.valueOf(e.status());

        if (status.is4xxClientError()) {
            return handleClientError(e, returnType);
        } else if (status.is5xxServerError()) {
            return handleServerError(e, returnType);
        } else {
            return handleUnexpectedError(e, returnType);
        }
    }

    private <T> T handleClientError(FeignException e, Class<T> returnType) {
        LOGGER.error("Client error occurred (status {}): {}", e.status(), e.getMessage());
        return createEmptyInstance(returnType);
    }

    private <T> T handleServerError(FeignException e, Class<T> returnType) {
        LOGGER.error("Server error occurred (status {}): {}", e.status(), e.getMessage());
        return createEmptyInstance(returnType);
    }

    private <T> T handleUnexpectedError(FeignException e, Class<T> returnType) {
        LOGGER.error("Unexpected error occurred: {}", e.getMessage());
        return createEmptyInstance(returnType);
    }

    private <T> T createEmptyInstance(Class<T> returnType) {
        try {
            return returnType.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            LOGGER.error("Failed to create empty instance", ex);
            throw new RuntimeException("Failed to create empty instance", ex);
        }
    }
}
