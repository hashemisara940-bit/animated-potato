package dev.kairo.domain.conversation;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record Message(UUID id, Role role, String content, Instant createdAt, Map<String, Object> metadata) {
    public Message {
        if (id == null || role == null || content == null || createdAt == null) {
            throw new IllegalArgumentException("message requires id, role, content, and creation time");
        }
        metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
    }

    public static Message of(Role role, String content) {
        return new Message(UUID.randomUUID(), role, content, Instant.now(), Map.of());
    }
}
