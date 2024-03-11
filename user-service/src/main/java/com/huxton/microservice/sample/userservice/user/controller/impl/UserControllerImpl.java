package com.huxton.microservice.sample.userservice.user.controller.impl;

import com.huxton.microservice.sample.userservice.user.controller.UserController;
import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import com.huxton.microservice.sample.userservice.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity<Page<UserDto>> getUsers(String search, Pageable pageable) throws Exception {
        return ResponseEntity.ok(service.getUsers(search, pageable));
    }

    @Override
    public ResponseEntity<UserDto> createUser(@NonNull UserDto dto) throws Exception {
        return ResponseEntity.ok(service.createUser(dto));
    }

    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) throws Exception {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }
}
