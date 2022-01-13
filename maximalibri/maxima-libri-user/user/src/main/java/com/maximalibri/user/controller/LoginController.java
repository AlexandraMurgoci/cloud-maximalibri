package com.maximalibri.user.controller;

import com.maximalibri.user.dto.UserIdAndRoles;
import com.maximalibri.user.dto.UserLoginDto;
import com.maximalibri.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserIdAndRoles> loginAndReturnRoles(@RequestBody UserLoginDto userLoginDto) {
        try {
            return new ResponseEntity<>(userService.verifyLogin(userLoginDto), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(new UserIdAndRoles(), HttpStatus.NOT_FOUND);
        }
    }
}
