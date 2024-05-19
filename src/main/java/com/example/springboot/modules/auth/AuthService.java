package com.example.springboot.modules.auth;

import com.example.springboot.modules.user.UserDetailServiceCore;
import com.example.springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailServiceCore userService;

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public String extractUserName(String token) {
        return jwtUtil.extractUserName(token);
    }

    public String authenticate(String username, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetail = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetail.getUsername());
    }

}
