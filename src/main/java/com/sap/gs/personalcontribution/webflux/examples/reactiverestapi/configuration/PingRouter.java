package com.sap.gs.personalcontribution.webflux.examples.reactiverestapi.configuration;

import com.sap.gs.personalcontribution.webflux.examples.reactiverestapi.controller.PingController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PingRouter {
    @Bean
    public RouterFunction<ServerResponse> ping(PingController pingController) {
        return RouterFunctions.route(RequestPredicates.GET("/ping"), pingController::ping);
    }
}
