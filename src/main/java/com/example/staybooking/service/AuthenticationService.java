package com.example.staybooking.service;

import com.example.staybooking.Util.JwtUtil;
import com.example.staybooking.exception.UserNotExistException;
import com.example.staybooking.model.Token;
import com.example.staybooking.model.User;
import com.example.staybooking.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole userRole) throws UserNotExistException {
        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        } catch (AuthenticationException e) {
            throw new UserNotExistException("User Not Exist!!!");
        }
        if (auth == null || !auth.isAuthenticated()
                || !auth.getAuthorities().contains(new SimpleGrantedAuthority(userRole.name()))) {
            throw new UserNotExistException("User Not Exist!!!");
        }
        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}