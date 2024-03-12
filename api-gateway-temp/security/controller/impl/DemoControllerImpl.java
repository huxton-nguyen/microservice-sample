package com.huxton.microservice.sample.apigateway.security.controller.impl;

import com.huxton.microservice.sample.apigateway.security.controller.DemoController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DemoControllerImpl implements DemoController {
    @Override
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello");
    }
}
