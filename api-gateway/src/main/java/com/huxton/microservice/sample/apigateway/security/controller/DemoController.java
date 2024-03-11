package com.huxton.microservice.sample.apigateway.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/v1/test")
public interface DemoController {

    @GetMapping
    ResponseEntity<String> test();
//
//    @GetMapping
//    public ResponseEntity<String> test() {
//
//        return ResponseEntity.ok("Hello");
//    }
}
