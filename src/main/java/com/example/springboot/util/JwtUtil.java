package com.example.springboot.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.month}")
    private int jwtExpirationMonth;
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ jwtExpirationMonth * 30 * 24 * 3600 * 1000L);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiryDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String extractUserName(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenvalid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        if (!this.isTokenvalid(jwt)) {
            return false;
        }

        String userNameFromToken = this.extractUserName(jwt);

        return userNameFromToken.equals(userDetails.getUsername());
    }
}
