package com.example.staybooking.service;

import com.example.staybooking.exception.UserAlreadyExistException;
import com.example.staybooking.model.Authority;
import com.example.staybooking.model.User;
import com.example.staybooking.model.UserRole;
import com.example.staybooking.repository.AuthorityRepository;
import com.example.staybooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegisterService {

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Autowired
    public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    //Transactional annotation to guarantee atomic operation in DB.
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole userRole) {
        if (userRepository.existsById(user.getUsername())) {
            throw new UserAlreadyExistException("User Already Exists!!!");
        }
        userRepository.save(user);
        authorityRepository.save(new Authority(user.getUsername(), userRole.name()));
    }
}
