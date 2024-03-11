package com.huxton.microservice.sample.apigateway.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails getUserByEmail(String email);
}
