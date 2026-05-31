package dev.kairo.domain.tool;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record ToolCall(UUID id, String toolName, Map<String, Object> arguments, Instant requestedAt) {
    public ToolCall {
        if (id == null || toolName == null || toolName.isBlank() || requestedAt == null) throw new IllegalArgumentException("tool call is invalid");
        arguments = arguments == null ? Map.of() : Map.copyOf(arguments);
    }

    public static ToolCall of(String toolName, Map<String, Object> arguments) {
        return new ToolCall(UUID.randomUUID(), toolName, arguments, Instant.now());
    }
}
