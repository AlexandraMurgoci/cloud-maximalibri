package com.maximalibri.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.maximalibri.user.dto.UserIdAndRoles;
import com.maximalibri.user.dto.UserLoginDto;
import com.maximalibri.user.dto.UserRegistrationDto;
import com.maximalibri.user.model.Role;
import com.maximalibri.user.model.RoleName;
import com.maximalibri.user.model.User;
import com.maximalibri.user.repository.RoleRepository;
import com.maximalibri.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserIdAndRoles verifyLogin(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if(user!=null && BCrypt.verifyer().verify(userLoginDto.getPassword().toCharArray(), user.getPassword()).verified)
            return new UserIdAndRoles(user.getId(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        else throw new RuntimeException("Bad Credentials");
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /** extrage si salveaza userul din formatul datelor din register */
    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setPassword(BCrypt.withDefaults().hashToString(12, registration.getPassword().toCharArray()));
        user.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER)));
        user.setActive(true);
        return userRepository.save(user);
    }
}
