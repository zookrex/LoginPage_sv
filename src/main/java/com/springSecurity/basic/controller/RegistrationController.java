package com.springSecurity.basic.controller;

import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.UserRepo;
import com.springSecurity.basic.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api1")
public class RegistrationController {
    @GetMapping
    public String test() {
        return "test";
    }

    //    private final UserRepo userRepo;
//    private final UserService userService;
//
//    public RegistrationController(UserRepo userRepo) {
//        this.userRepo = userRepo;
//        this.userService = new UserService();
//    }
//    @PostMapping("/registerUser")
//    public ResponseEntity<User> userRegistration(@RequestBody User user) {
//        User userWithRole = userService.setUserRole(user);
//        userRepo.save(userWithRole);
//        return ResponseEntity.ok().build();
//    }
}
