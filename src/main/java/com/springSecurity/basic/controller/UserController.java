package com.springSecurity.basic.controller;

import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.UserRepo;
import com.springSecurity.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class UserController {

    private final UserRepo userRepo;
    @Autowired
    private  UserService userService;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;

    }
    @GetMapping("/test")
    public User test() {
        return userRepo.findById(1);
    }



    @PostMapping("/userLogin")
    public ResponseEntity<User> userLogin(@RequestBody User userData) {
        User user=userService.authenticate(userData.getUsername(), userData.getPassword());
        if(user!=null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(user);
    }


}
