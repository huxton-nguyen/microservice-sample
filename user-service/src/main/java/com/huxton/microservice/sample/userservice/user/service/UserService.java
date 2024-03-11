package com.huxton.microservice.sample.userservice.user.service;

import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> getUsers(String search, Pageable pageable);

    UserDto createUser(UserDto dto);
}
