package com.muates.identityservice.controller;

import com.muates.identityservice.model.dto.request.LoginRequest;
import com.muates.identityservice.model.dto.response.JwtResponse;
import com.muates.identityservice.service.AuthService;
import com.muates.identityservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(
                authentication.getName(),
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String url,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return authService.validate(url, token);
    }
}

