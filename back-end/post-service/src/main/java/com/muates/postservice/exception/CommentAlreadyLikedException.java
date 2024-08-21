package com.muates.postservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommentAlreadyLikedException extends RuntimeException {
    public CommentAlreadyLikedException() {
        super();
    }

    public CommentAlreadyLikedException(String message) {
        super(message);
    }

    public CommentAlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }
}
