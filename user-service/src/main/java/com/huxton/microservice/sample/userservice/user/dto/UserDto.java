package com.huxton.microservice.sample.userservice.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {

    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;
    @NotBlank(message = "Name is required")
    @Email
    @Size(max = 255)
    private String email;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Length(min = 6, max = 255)
    private String password;
}
