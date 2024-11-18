package com.springSecurity.basic.controller;

import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.UserRepo;
import com.springSecurity.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepo userRepo;
    @Autowired
    private  UserService userService;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    @GetMapping("/api1")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> userRegistration(@RequestBody User user) {
        User userWithRole = userService.setUserRole(user);
        userRepo.save(userWithRole);
        return ResponseEntity.ok().build();
    }


}
