package com.sap.gssi.health.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import reactor.test.StepVerifier;

class HealthControllerTest {

    private final HealthController healthController = new HealthController();

    @Test
    void health() {
        StepVerifier.create(healthController.health())
                .expectNext(ResponseEntity.ok().body(HealthController.RESPONSE))
                .verifyComplete();
    }
}