package com.arzoo.login.service;

import com.arzoo.login.domain.User;
import com.arzoo.login.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder encrypt;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
        this.encrypt=new BCryptPasswordEncoder();
    }

    public void registerUser(User user){
        String encryptPassword=encrypt.encode(user.getPassword());
       // log.info("-------->"+encrypt.matches(user.getPassword(),encryptPassword));
        user.setPassword(encryptPassword);
        userRepo.save(user);

    }
}
