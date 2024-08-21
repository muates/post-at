package com.muates.identityservice.service.impl;

import com.muates.identityservice.model.dto.request.LoginRequest;
import com.muates.identityservice.security.CustomUserDetails;
import com.muates.identityservice.service.AuthService;
import com.muates.identityservice.service.JwtService;
import com.muates.identityservice.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RolePermissionService rolePermissionService;

    @Override
    public String authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return jwtService.generateToken(
                authentication.getName(),
                userId,
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }

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
