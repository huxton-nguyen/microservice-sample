package com.huxton.microservice.sample.apigateway.security.service.impl;

import com.huxton.microservice.sample.apigateway.security.dao.RefreshTokenDao;
import com.huxton.microservice.sample.apigateway.security.dto.AccountDto;
import com.huxton.microservice.sample.apigateway.security.dto.TokenDto;
import com.huxton.microservice.sample.apigateway.security.dto.UserDto;
import com.huxton.microservice.sample.apigateway.security.model.RefreshToken;
import com.huxton.microservice.sample.apigateway.security.service.AuthenticationService;
import com.huxton.microservice.sample.apigateway.security.service.JwtService;
import com.huxton.microservice.sample.apigateway.user.dao.UserDao;
import com.huxton.microservice.sample.apigateway.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value(value = "${jwt.refresh.token.expiration}")
    private long refreshTokenExpiration;

    private final UserDao dao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenDao refreshTokenDao;

    @Override
    public TokenDto register(UserDto dto) {

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        user = dao.save(user);

        String refreshToken = UUID.randomUUID().toString();
        RefreshToken refreshTokenEntity = saveRefreshToken(refreshToken, user);

        return TokenDto.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(refreshTokenEntity.getToken())
                .build();
    }

    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public TokenDto authenticate(AccountDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        User user = dao.findByEmail(dto.getEmail()).orElseThrow();

        String refreshToken = UUID.randomUUID().toString();
        RefreshToken refreshTokenEntity = saveRefreshToken(refreshToken, user);

        return TokenDto.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(refreshTokenEntity.getToken())
                .build();
    }

    @Override
    public TokenDto refreshToken(String refreshToken) {

        RefreshToken entity = refreshTokenDao.findByToken(refreshToken).orElseThrow();
        entity.setExpiryDate(new Date(System.currentTimeMillis() + refreshTokenExpiration));
        entity = refreshTokenDao.save(entity);

        return TokenDto.builder()
                .accessToken(jwtService.generateRefreshToken(entity.getUser()))
                .refreshToken(refreshToken)
                .build();
    }

    private RefreshToken saveRefreshToken(
            String refreshToken,
            User user
    ) {

        RefreshToken entity = RefreshToken.builder()
                .token(refreshToken)
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .build();

        return refreshTokenDao.save(entity);
    }
}
