package com.muates.identityservice.controller;

import com.muates.identityservice.model.dto.request.RolePermissionRequest;
import com.muates.identityservice.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role-permission")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @PostMapping("/add")
    public ResponseEntity<?> addRoleToEndpoint(@RequestBody RolePermissionRequest request) {
        String response = rolePermissionService.addRoleToEndpoint(request);
        return ResponseEntity.ok(response);
    }
}
