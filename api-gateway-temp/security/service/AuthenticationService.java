package com.huxton.microservice.sample.apigateway.security.service;

import com.huxton.microservice.sample.apigateway.security.dto.AccountDto;
import com.huxton.microservice.sample.apigateway.security.dto.TokenDto;
import com.huxton.microservice.sample.apigateway.security.dto.UserDto;

public interface AuthenticationService {
    TokenDto register(UserDto dto);
    TokenDto authenticate(AccountDto dto);
    TokenDto refreshToken(String refreshToken);

}
