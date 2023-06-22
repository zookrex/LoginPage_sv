package com.arzoo.login.controller;


import com.arzoo.login.domain.User;
import com.arzoo.login.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/login")
public class Login {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String test(){
        return "yayyyy";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        log.info("yay");
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser!=null){
            return ResponseEntity.badRequest().body("User Already exist!!!!!!!");
        }
        user.setPassword(user.getPassword());
        userRepo.save(user);
        return ResponseEntity.ok("User Created withe username : "+user.getUsername());
    }
}
