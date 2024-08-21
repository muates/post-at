package com.muates.identityservice.service;

import com.muates.identityservice.model.dto.request.RegisterRequest;
import com.muates.identityservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(RegisterRequest request);
    Optional<User> findByUsername(String username);
    List<User> findAll();
}
