package com.muates.identityservice.service.impl;

import com.muates.identityservice.model.dto.request.RolePermissionRequest;
import com.muates.identityservice.model.entity.RolePermission;
import com.muates.identityservice.repository.RolePermissionRepository;
import com.muates.identityservice.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public String addRoleToEndpoint(RolePermissionRequest request) {
        RolePermission rolePermission = RolePermission.builder()
                .role(request.getRole())
                .endpoint(request.getEndpoint())
                .build();

        rolePermissionRepository.save(rolePermission);

        return "Role added to endpoint";
    }

    @Override
    public List<String> findRoleByEndpoint(String endpoint) {
        return rolePermissionRepository.findRoleByEndpoint(endpoint);
    }
}
