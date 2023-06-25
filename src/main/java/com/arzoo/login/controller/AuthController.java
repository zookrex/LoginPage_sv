package com.arzoo.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arzoo.login.config.JwtTokenUtil;
import com.arzoo.login.domain.User;
import com.arzoo.login.service.AuthService;
import com.arzoo.login.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody  User user){
		
		
		userService.registerUser(user);
		
		
		return  ResponseEntity.ok("User Registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody User user){
		if(authService.authenticateUser(user)) {
			String role=authService.getUserRole(user.getUsername());
			String token= jwtTokenUtil.generateToken(user.getUsername(), role);
			return ResponseEntity.ok(token);
		}
		else
			return ResponseEntity.status(401).body("Invalid credentials.");
	}

}
