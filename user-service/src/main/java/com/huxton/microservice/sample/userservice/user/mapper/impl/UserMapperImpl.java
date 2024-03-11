package com.huxton.microservice.sample.userservice.user.mapper.impl;

import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import com.huxton.microservice.sample.userservice.user.mapper.UserMapper;
import com.huxton.microservice.sample.userservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto mapDtoFromModel(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User mapModelFromDto(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
}
