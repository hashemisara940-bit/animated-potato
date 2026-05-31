package dev.kairo.domain.knowledge;

import java.util.UUID;

public record Chunk(UUID id, UUID documentId, int index, String content) {}
