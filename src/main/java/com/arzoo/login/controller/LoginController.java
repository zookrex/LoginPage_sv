package com.arzoo.login.controller;

//
//import com.arzoo.login.domain.User;
//import com.arzoo.login.repo.UserRepo;
//import com.arzoo.login.service.UserService;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import java.util.HashMap;
//import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private UserService userService;
//    
//    
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<L>
//    
    
    
    
    
    
    
//
//    @Autowired
//    BCryptPasswordEncoder encoder;
//
//
//    private String response;
//    @PostMapping("/register")
//    public ResponseEntity<Map<String,String>> registerUser(@RequestBody User  user){
// //       log.info("yay");
//        String res="";Map<String,String> r1=new HashMap<>();
//        User existingUser = userRepo.findByUsername(user.getUsername());
//        if(existingUser!=null){
//            res="User alredy exsists";
//            r1.put("msg",res);
//            return ResponseEntity.badRequest().body(r1);
//        }
//
//        userService.registerUser(user);
//        res="User Registered";
//        r1.put("msg",res);
//        return ResponseEntity.ok().body(r1);
//    }
//    @PostMapping ("/login")
//    public ResponseEntity<Map<String,String>> loginUser(@RequestBody User user){
////        log.info("inside login api");
//        Map<String,String> r1=new HashMap<>();
//        User existingUser= userRepo.findByUsername(user.getUsername());
//        if(existingUser==null){
//            response="User is not Registered";
//            r1.put("msg",response);
//            return ResponseEntity.badRequest().body(r1);
//        }
//
//        if(encoder.matches(user.getPassword(), existingUser.getPassword())) {
//            response="Login Successful";
//            r1.put("msg",response);
//            return ResponseEntity.ok().body(r1);
//        }
//        response="Chack Credentials";
//        r1.put("msg",response);
//        return ResponseEntity.badRequest().body(r1);
//    }
//
//
}
