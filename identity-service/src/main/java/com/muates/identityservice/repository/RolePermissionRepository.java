package com.muates.identityservice.repository;

import com.muates.identityservice.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<String> findRoleByEndpoint(String endpoint);
}
