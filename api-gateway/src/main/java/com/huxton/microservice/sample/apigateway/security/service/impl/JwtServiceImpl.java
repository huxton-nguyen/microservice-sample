package com.huxton.microservice.sample.apigateway.security.service.impl;

import com.huxton.microservice.sample.apigateway.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value(value = "${jwt.secret.key}")
    private String SECRET_KEY;
    @Value(value = "${jwt.refresh.token.expiration}")
    private long expirationTime;
    @Value(value = "${jwt.refresh.token.expiration}")
    private long refreshTokenExpiryTime;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimResolver) {

        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    @Override
    public String generateToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, expirationTime);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, refreshTokenExpiryTime);
    }

    private String buildToken(HashMap<String, ?> claims, UserDetails user, long expirationTime) {

        return Jwts.builder()
                .claims(claims)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(user.getUsername())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        // Build Jwt parser
        // parse token
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {

        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(decodedKey);
    }
}
