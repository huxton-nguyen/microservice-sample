package com.huxton.microservice.sample.apigateway.security.service.impl;

import com.huxton.microservice.sample.apigateway.security.dto.UserDto;
import com.huxton.microservice.sample.apigateway.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WebClient.Builder clientBuilder;

    @Override
    public UserDetails getUserByEmail(String email) {
        WebClient webClient = clientBuilder
                .baseUrl("http://localhost:8080/api/v1/users/email/%s".formatted(email))
                .build();

        WebClient.ResponseSpec res = webClient
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return res.bodyToMono(UserDto.class).block();
    }
}
