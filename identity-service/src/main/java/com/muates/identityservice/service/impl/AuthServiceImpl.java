package com.muates.identityservice.service.impl;

import com.muates.identityservice.service.AuthService;
import com.muates.identityservice.service.JwtService;
import com.muates.identityservice.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final RolePermissionService rolePermissionService;

    @Override
    public ResponseEntity<String> validate(String url, String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not found");
        }

        token = token.substring(7);

        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        List<String> userRoles = jwtService.getRolesFromToken(token);
        if (isAuthorized(url, userRoles)) {
            return ResponseEntity.ok("User is authorized");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authorized");
    }

    public boolean isAuthorized(String endpoint, List<String> userRoles) {
        List<String> roles = rolePermissionService.findRoleByEndpoint(endpoint);
        if (roles == null || roles.isEmpty()) {
            return false;
        }

        for (String role : userRoles) {
            if (roles.contains(role)) {
                return true;
            }
        }

        return false;
    }
}
