package com.huxton.microservice.sample.apigateway.security.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDto {

    private int status;
    private String message;
}
