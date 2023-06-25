package com.arzoo.login.service;

import org.springframework.stereotype.Service;

import com.arzoo.login.config.JwtTokenUtil;
import com.arzoo.login.domain.User;
import com.arzoo.login.repo.UserRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.eclipse.jetty.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AuthService   {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
//	
//	@Autowired
//	private AuthenticationManager authenticationManagerBean;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
//	public void registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.registerUser(user);
//    }
	
	public boolean authenticateUser(User user) {

		User exsistingUser = userRepo.findByUsername(user.getUsername());
		return exsistingUser!=null && passwordEncoder
				.matches(user.getPassword(), exsistingUser.getPassword());
				
		
	}
	
	public String getUserRole(String username) {
		User user=userRepo.findByUsername(username);
		return user!=null? user.getRole().toString():null;
	}
	
//    public String authenticate(User user) throws Exception {
//        try {
//        	authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid username or password", e);
//        }
//
//        final UserDetails userDetails = loadUserByUsername(username);
//        return jwtTokenUtil.generateToken(userDetails);
//    }
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		User user=userRepo.findByUsername(username);
//		
//		if(user==null)
//			throw new UsernameNotFoundException("User Not Found!!!!!!");
//		
//		return org.springframework.security.core.userdetails.User.withUsername(username)
//				.password(user.getPassword())
//				.authorities(user.getRole().name())
//				.accountExpired(false)
//				.accountLocked(false)
//				.credentialsExpired(false)
//				.disabled(false)
//				.build();
//	}
	
	
	
	

}
