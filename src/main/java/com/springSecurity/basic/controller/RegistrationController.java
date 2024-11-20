package com.springSecurity.basic.controller;

import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.UserRepo;
import com.springSecurity.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String test() {
        return "test";
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> userRegistration(@RequestBody User user) {
        User userWithRole = userService.setRoleForNewUser(user);
        userRepo.save(userWithRole);
        return ResponseEntity.ok().build();
    }
}
