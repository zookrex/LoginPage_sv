package com.arzoo.login.controller;


import com.arzoo.login.domain.User;
import com.arzoo.login.repo.UserRepo;
import com.arzoo.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
public class Login {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;


    private String response;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User  user){
        log.info("yay");
        String res="";
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser!=null){
            res="User alredy exsists";
            return ResponseEntity.badRequest().body(res);
        }

        userService.registerUser(user);
        res="User Registered";
        return ResponseEntity.ok().body(res);
    }
    @PostMapping ("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        log.info("inside login api");
        User existingUser= userRepo.findByUsername(user.getUsername());
        if(existingUser==null){
            response="User is not Registered";
            return ResponseEntity.badRequest().body(response);
        }

        if(encoder.matches(user.getPassword(), existingUser.getPassword())) {
            response="Login Successful";
            return ResponseEntity.ok().body(response);
        }
        response="Chack Credentials";
        return ResponseEntity.badRequest().body(response);
    }


}
