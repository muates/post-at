package com.muates.identityservice.controller;

import com.muates.identityservice.model.dto.request.LoginRequest;
import com.muates.identityservice.model.dto.response.JwtResponse;
import com.muates.identityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String url,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return authService.validate(url, token);
    }
}

