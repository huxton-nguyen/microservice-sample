package com.huxton.microservice.sample.apigateway.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {

    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private String accessToken;
    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private String refreshToken;
}
