package com.huxton.microservice.sample.userservice.user.controller;

import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/users")
public interface UserController {

    @GetMapping(
            path = "",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<UserDto>> getUsers(@RequestParam(name = "search", required = false, defaultValue = "") String search,
                                           @PageableDefault(page = 0, size = 20)
                                           @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
            throws Exception;

    @PostMapping(
            path = "",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDto> createUser(@RequestBody @NonNull UserDto dto) throws Exception;
}
