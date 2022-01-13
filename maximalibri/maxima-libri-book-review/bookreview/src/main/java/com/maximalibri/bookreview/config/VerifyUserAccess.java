package com.maximalibri.bookreview.config;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VerifyUserAccess {

    public boolean verify(String userEmail, String userPassword, Set<RoleName> requiredRoles) {
        //TODO
        return true;
    }
}
