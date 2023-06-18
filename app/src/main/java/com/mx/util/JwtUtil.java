package com.mx.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${application.jwt.secret}")
    private String secret;

    public String generateAccessToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setSubject(String.format("%s", username))
                .setIssuer("me")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(
                    key,
                    SignatureAlgorithm.HS256
                ).compact();
    }

    public String getUsernameFromAuth(String AuthKey){
        String token = this.getTokenFromAuth(AuthKey);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims claims = jws.getBody();
        String username = claims.getSubject();
        return username;
    }

    public String getTokenFromAuth(String bearerKey) {
        return bearerKey.substring(7, bearerKey.length());
    }
}