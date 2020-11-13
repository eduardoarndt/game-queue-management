package com.sap.gs.personalcontribution.webflux.examples.individualexamples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class test {
    public static void main(String[] args) {
        Mono<Integer> integerMono = Mono.just(10);

        integerMono.map(value -> {
            return value / 0;
        })
        .onErrorReturn(0)
        .doOnNext(System.out::println)
        .subscribe();

        /*
        Flux<String> colors = Flux.just("red", "black", "tan");
        Flux<String> results = processColors(colors);

        StepVerifier.create(results).expectNext("processed red", "default", "processed tan")
                .verifyComplete();
         */

    }

    public static Flux<String> processColors(Flux<String> colors) {
        return colors.concatMap(color ->
                simulateRemoteCall(color)
                        .timeout(Duration.ofMillis(400)));

    }

    public static Mono<String> simulateRemoteCall(String input) {
        int delay = input.length() * 100;
        return Mono.just(input)
                .doOnNext(s -> System.out.println("Received " + s + ", delaying for " + delay))
                .map(i -> "processed " + i)
                .delayElement(Duration.of(delay, ChronoUnit.MILLIS));
    }
}
