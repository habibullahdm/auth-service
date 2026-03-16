package com.habibullahdm.auth.security;

import com.habibullahdm.auth.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    public String generateToken(String userId, String username, List<String> roles) {
        return Jwts.builder()
                .subject(userId)
                .claim(Constants.USERNAME, username)
                .claim(Constants.ROLES, roles)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(Keys.hmacShaKeyFor(Constants.SECRET.getBytes()))
                .compact();
    }

    public String extractUserId(String token) {
        return parseToken(token).getSubject();
    }

    public String extractUsername(String token) {
        return parseToken(token).get("username", String.class);
    }

    public List<String> extractRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Constants.SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
