package com.muates.identityservice.service;

import com.muates.identityservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
    List<User> findAll();
}
