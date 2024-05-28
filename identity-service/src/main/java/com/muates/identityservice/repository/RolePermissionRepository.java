package com.muates.identityservice.repository;

import com.muates.identityservice.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    @Query("SELECT rp.role FROM RolePermission rp WHERE rp.endpoint = :endpoint")
    List<String> findRoleByEndpoint(@Param("endpoint") String endpoint);
}
