package com.muates.identityservice.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("User Role"),
    ROLE_ADMIN("Admin Role");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }
}
