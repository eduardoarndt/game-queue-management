package com.sap.gs.personalcontribution.webflux.examples.individual;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxExample {
    public static void main(String[] args) {
        Flux<Integer> values = Flux.just(1, 2, 3, 4, 5);
        List<Integer> doubleTheValues = new ArrayList<>();

        values.map(eachValue -> doubleTheValues.add(eachValue * 2))
                .doOnComplete(() -> System.out.println(doubleTheValues))
                .subscribe();

    }
}