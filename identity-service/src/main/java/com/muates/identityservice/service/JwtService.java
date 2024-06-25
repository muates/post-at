package com.muates.identityservice.service;

import java.util.List;

public interface JwtService {
    String generateToken(String username, List<String> roles);
    List<String> getRolesFromToken(String token);
    boolean validateToken(String token);
}
