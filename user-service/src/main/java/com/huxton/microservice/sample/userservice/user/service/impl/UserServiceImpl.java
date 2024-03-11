package com.huxton.microservice.sample.userservice.user.service.impl;

import com.huxton.microservice.sample.userservice.user.dao.UserDao;
import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import com.huxton.microservice.sample.userservice.user.mapper.UserMapper;
import com.huxton.microservice.sample.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public Page<UserDto> getUsers(String search, Pageable pageable) {
        return userDao.getUsers(search, pageable).map(userMapper::mapDtoFromModel);
    }

    @Override
    public UserDto createUser(UserDto dto) {
        return userMapper.mapDtoFromModel(userDao.save(userMapper.mapModelFromDto(dto)));
    }
}
