package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("tokenType", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() * 1000L * 60 * 5))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("tokenType", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() * 1000L * 60 * 60 * 24 * 30))
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromAccessToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String tokenType = claims.get("tokenType").toString();
        if(!tokenType.equals("access")){
            throw new IllegalArgumentException("Please provide the correct access Token");
        }
        return Long.valueOf(claims.getSubject());
    }

    public Long getUserIdFromRefreshToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String tokenType = claims.get("tokenType").toString();
        if(!tokenType.equals("refresh")){
            throw new IllegalArgumentException("Please provide the correct refresh Token");
        }
        return Long.valueOf(claims.getSubject());
    }
}
