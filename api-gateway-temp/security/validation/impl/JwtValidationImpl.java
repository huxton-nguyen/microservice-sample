//package com.huxton.microservice.sample.apigateway.security.validation.impl;
//
//import com.huxton.microservice.sample.apigateway.security.service.JwtService;
//import com.huxton.microservice.sample.apigateway.security.validation.JwtValidation;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.ServletException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.Objects;
//
//@Service
//@RequiredArgsConstructor
//public class JwtValidationImpl implements JwtValidation {
//
//    @Value(value = "${jwt.token.type}")
//    private String AUTHORIZATION_TYPE;
//
//    private final JwtService jwtService;
//
//    public void validateAuthHeader(String authHeader) throws ServletException {
//
//        requiredAuthHeader(authHeader);
//        validateAuthHeaderFormat(authHeader);
//    }
//
//    @Override
//    public void validateJwtToken(String jwtToken, UserDetails userDetails) throws ServletException {
//
//        String username = jwtService.extractUsername(jwtToken);
//
//        if (!username.equals(userDetails.getUsername())) throw new ServletException("User not found");
//
//        validateExpiration(jwtService.extractClaim(jwtToken, Claims::getExpiration));
//    }
//
//    private void validateExpiration(Date expiration) throws ServletException {
//
//        if (expiration.before(new Date(System.currentTimeMillis()))) throw new ServletException("Token expired");
//    }
//
//    private void requiredAuthHeader(String authHeader) throws ServletException {
//
//        if (Objects.isNull(authHeader) || authHeader.isBlank()) throw new ServletException("Token is missing");
//    }
//
//    private void validateAuthHeaderFormat(String authHeader) throws ServletException {
//
//        if (!authHeader.startsWith(AUTHORIZATION_TYPE)) throw new ServletException("Token is wrong format");
//    }
//}
