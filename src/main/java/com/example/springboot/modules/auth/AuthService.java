package com.example.springboot.modules.auth;

import com.example.springboot.modules.user.UserEntity;
import com.example.springboot.modules.user.UserService;
import com.example.springboot.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ 30 * 24 * 3600 * 1000L);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiryDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, "jwtSecret")
                .compact();
    }

    public String extractUserName(String token) {
        return Jwts.parser()
                .setSigningKey("jwtSecret")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String authenticate(String username, String password) {
        try {
            String encodedPassword = passwordEncoder.encode(password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, encodedPassword));
        } catch (AuthenticationException e) {
            throw new Error("Incorrect Username And Password", e);
        }

        final UserDetails userDetail = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetail.getUsername());
    }


}
