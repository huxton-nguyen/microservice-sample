package com.huxton.microservice.sample.apigateway.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {

    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimResolver);
    String generateToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
}
