package com.huxton.microservice.sample.userservice.user.mapper;

import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import com.huxton.microservice.sample.userservice.user.model.User;

public interface UserMapper {
    UserDto mapDtoFromModel(User user);

    User mapModelFromDto(UserDto dto);
}
