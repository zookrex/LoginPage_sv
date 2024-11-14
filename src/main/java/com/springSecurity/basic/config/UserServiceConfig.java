package com.springSecurity.basic.config;


import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.UserRepo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceConfig implements UserDetailsService {


    private final UserRepo userRepo;

    public UserServiceConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user=userRepo.findByUsername(username);
        System.out.println("Username :"+user.getUsername());

        Set<GrantedAuthority> authorities=user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}
