package com.muates.identityservice.service;

import com.muates.identityservice.model.dto.request.RolePermissionRequest;

import java.util.List;

public interface RolePermissionService {
    String addRoleToEndpoint(RolePermissionRequest request);
    List<String> findRoleByEndpoint(String endpoint);
}
