package com.sap.gssi.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthController {

    protected static final String RESPONSE = "UP";

    @GetMapping(path = "/health")
    public Mono<ResponseEntity<String>> health() {
        return Mono.just(ResponseEntity.ok().body(RESPONSE));
    }
}
