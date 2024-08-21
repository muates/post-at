package com.muates.identityservice.service.impl;

import com.muates.identityservice.clients.MemberServiceClient;
import com.muates.identityservice.model.dto.request.MemberCreateRequestClient;
import com.muates.identityservice.model.dto.request.RegisterRequest;
import com.muates.identityservice.model.entity.User;
import com.muates.identityservice.model.entity.enums.Role;
import com.muates.identityservice.repository.UserRepository;
import com.muates.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberServiceClient memberServiceClient;

    @Override
    public User saveUser(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(Role.valueOf("ROLE_USER")))
                .build();

        userRepository.save(user);

        memberServiceClient.createMember(
                MemberCreateRequestClient.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build()
        );

        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
