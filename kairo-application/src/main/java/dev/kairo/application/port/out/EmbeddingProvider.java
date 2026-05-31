package dev.kairo.application.port.out;

import reactor.core.publisher.Mono;

import java.util.List;

public interface EmbeddingProvider {
    String id();
    Mono<List<Double>> embed(String input, String model);
}
