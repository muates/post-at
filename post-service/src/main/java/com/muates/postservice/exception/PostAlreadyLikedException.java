package com.muates.postservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostAlreadyLikedException extends RuntimeException {
    public PostAlreadyLikedException() {
        super();
    }

    public PostAlreadyLikedException(String message) {
        super(message);
    }

    public PostAlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
