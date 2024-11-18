package com.springSecurity.basic.service;

import com.springSecurity.basic.entity.Roles;
import com.springSecurity.basic.entity.User;
import com.springSecurity.basic.repo.RolesRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RolesRepo rolesRepo;

    // Constructor injection
    public UserService(PasswordEncoder passwordEncoder, RolesRepo rolesRepo) {
        this.passwordEncoder = passwordEncoder;
        this.rolesRepo = rolesRepo;
    }

    public User setUserRole(User user) {
        Set<Roles> roles = new HashSet<>();
        roles.add(rolesRepo.findById(1));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(roles);
        return user;
    }
}
