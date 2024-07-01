package com.muates.identityservice.service;

import com.muates.identityservice.model.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String authenticateUser(LoginRequest request);
    ResponseEntity<String> validate(String url, String token);
}
