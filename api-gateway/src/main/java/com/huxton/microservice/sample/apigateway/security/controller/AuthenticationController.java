package com.huxton.microservice.sample.apigateway.security.controller;

import com.huxton.microservice.sample.apigateway.security.dto.AccountDto;
import com.huxton.microservice.sample.apigateway.security.dto.TokenDto;
import com.huxton.microservice.sample.apigateway.security.dto.UserDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(path = "/api/v1/authentications")
public interface AuthenticationController {

    @PostMapping(path = "/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TokenDto> refreshToken();

    @PostMapping(
            path = "",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TokenDto> authenticate(@RequestBody @NonNull @Valid AccountDto dto);

    @PostMapping(
            path = "/register",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TokenDto> register(@RequestBody @NonNull @Valid UserDto dto);

}
