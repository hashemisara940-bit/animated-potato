package dev.kairo.domain.tool;

import java.time.Instant;
import java.util.Map;

public record ToolResult(String toolName, boolean success, String output, Map<String, Object> metadata, Instant completedAt) {
    public ToolResult {
        metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
        completedAt = completedAt == null ? Instant.now() : completedAt;
    }

    public static ToolResult success(String toolName, String output) { return new ToolResult(toolName, true, output, Map.of(), Instant.now()); }
    public static ToolResult failure(String toolName, String output) { return new ToolResult(toolName, false, output, Map.of(), Instant.now()); }
}
