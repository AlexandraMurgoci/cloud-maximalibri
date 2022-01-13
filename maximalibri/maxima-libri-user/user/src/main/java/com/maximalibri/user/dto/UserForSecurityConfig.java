package com.maximalibri.user.dto;

import com.maximalibri.user.model.RoleName;

import java.util.Set;

public class UserForSecurityConfig {
    private String email;
    private String passwordHash;
    private Set<RoleName> roles;

    public UserForSecurityConfig(String email, String passwordHash, Set<RoleName> roles) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    public UserForSecurityConfig() {
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }
}
