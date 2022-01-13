package com.maximalibri.user.repository;

import com.maximalibri.user.model.Role;
import com.maximalibri.user.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleName roleName);
}
