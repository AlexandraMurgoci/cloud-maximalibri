package com.maximalibri.user.controller;

import com.maximalibri.user.dto.PrimitiveWrapper;
import com.maximalibri.user.dto.UserRegistrationDto;
import com.maximalibri.user.model.User;
import com.maximalibri.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<PrimitiveWrapper<String>> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        User existing = userService.findByEmail(userRegistrationDto.getEmail());
        if(existing!=null) {
            return new ResponseEntity<>(new PrimitiveWrapper<>("There is already an account registered with that email"), HttpStatus.NOT_ACCEPTABLE);
        }
        existing = userService.getUserByUsername(userRegistrationDto.getUsername());
        if(existing!=null) {
            return new ResponseEntity<>(new PrimitiveWrapper<>("There is already an account registered with that username"), HttpStatus.NOT_ACCEPTABLE);
        }

        userService.save(userRegistrationDto);

        return new ResponseEntity<>(new PrimitiveWrapper<>("success"), HttpStatus.CREATED);
    }
}
