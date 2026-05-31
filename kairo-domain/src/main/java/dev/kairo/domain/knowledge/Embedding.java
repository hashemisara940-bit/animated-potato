package dev.kairo.domain.knowledge;

import java.util.List;
import java.util.UUID;

public record Embedding(UUID chunkId, String model, List<Double> vector) {}
