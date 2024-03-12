package com.huxton.microservice.sample.apigateway.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountDto {

    @JsonProperty(
            required = true,
            access = JsonProperty.Access.WRITE_ONLY
    )
    @NotBlank(message = "Email khong duoc trong")
    private String email;
    @JsonProperty(
            required = true,
            access = JsonProperty.Access.WRITE_ONLY
    )
    @NotBlank(message = "Password khong duoc trong")
    private String password;
}
