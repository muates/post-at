package com.muates.identityservice.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> validate(String url, String token);
}
