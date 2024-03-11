package com.huxton.microservice.sample.apigateway.security.controller.impl;

import com.huxton.microservice.sample.apigateway.security.controller.AuthenticationController;
import com.huxton.microservice.sample.apigateway.security.dto.AccountDto;
import com.huxton.microservice.sample.apigateway.security.dto.TokenDto;
import com.huxton.microservice.sample.apigateway.security.dto.UserDto;
import com.huxton.microservice.sample.apigateway.security.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService service;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<TokenDto> refreshToken() {
        System.out.println(request.getCookies()[0].getValue());

        String refreshToken = getCookie(request, "refresh_token");
        return ResponseEntity.ok(service.refreshToken(refreshToken));
    }

    private String getCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();

        return Optional.ofNullable(Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals(name))
                        .toList().get(0))
                .map(Cookie::getValue)
                .orElseThrow();
    }

    @Override
    public ResponseEntity<TokenDto> authenticate(@NonNull AccountDto dto) {
        return ResponseEntity.ok(service.authenticate(dto));
    }

    @Override
    public ResponseEntity<TokenDto> register(@NonNull UserDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }
}
