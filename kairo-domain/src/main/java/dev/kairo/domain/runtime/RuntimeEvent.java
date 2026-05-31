package dev.kairo.domain.runtime;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record RuntimeEvent(UUID id, String type, String message, Map<String, Object> attributes, Instant occurredAt) {
    public RuntimeEvent {
        attributes = attributes == null ? Map.of() : Map.copyOf(attributes);
        occurredAt = occurredAt == null ? Instant.now() : occurredAt;
    }

    public static RuntimeEvent of(String type, String message) {
        return new RuntimeEvent(UUID.randomUUID(), type, message, Map.of(), Instant.now());
    }
}
