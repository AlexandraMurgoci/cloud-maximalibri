package com.maximalibri.user.dto;

import com.maximalibri.user.model.RoleName;

import java.util.HashSet;
import java.util.Set;

public class UserIdAndRoles {
    public Integer userId;
    public Set<RoleName> roles;

    public UserIdAndRoles() {
        userId = 0;
        roles = new HashSet<>();
    }

    public UserIdAndRoles(Integer userId, Set<RoleName> roles) {
        this.userId = userId;
        this.roles = roles;
    }
}
