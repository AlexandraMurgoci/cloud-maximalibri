package com.maximalibri.bookreview.config;

import java.util.Set;

public class User {
    private String email;
    private String passwordHash;
    private Set<RoleName> roles;

    public User() {
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
