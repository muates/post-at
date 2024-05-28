package com.muates.identityservice.model.dto.request;

import lombok.Data;

@Data
public class RolePermissionRequest {
    private String role;
    private String endpoint;
}
