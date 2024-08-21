package com.muates.postservice.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignClientExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientExceptionHandler.class);

    public void handleFeignException(FeignException e) {
        HttpStatus status = HttpStatus.valueOf(e.status());

        if (status.is4xxClientError()) {
            handleClientError(e);
        } else if (status.is5xxServerError()) {
            handleServerError(e);
        } else {
            handleUnexpectedError(e);
        }
    }

    private void handleClientError(FeignException e) {
        LOGGER.error("Client error occurred (status {}): {}", e.status(), e.getMessage());
    }

    private void handleServerError(FeignException e) {
        LOGGER.error("Server error occurred (status {}): {}", e.status(), e.getMessage());
    }

    private void handleUnexpectedError(FeignException e) {
        LOGGER.error("Unexpected error occurred: {}", e.getMessage());
    }
}
