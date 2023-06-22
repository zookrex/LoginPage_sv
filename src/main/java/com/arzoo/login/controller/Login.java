package com.arzoo.login.controller;


import com.arzoo.login.domain.User;
import com.arzoo.login.repo.UserRepo;
import com.arzoo.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/login")
public class Login {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        log.info("yay");
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser!=null){
            return ResponseEntity.badRequest().body("User Already exist!!!!!!!");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User Created withe username : "+user.getUsername());
    }
}
