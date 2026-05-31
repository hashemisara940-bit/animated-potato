package dev.kairo.domain.tool;

import java.util.Map;

public record ToolDefinition(ToolMetadata metadata, Map<String, Object> inputSchema) {
    public ToolDefinition {
        if (metadata == null) throw new IllegalArgumentException("tool metadata is required");
        inputSchema = inputSchema == null ? Map.of() : Map.copyOf(inputSchema);
    }
}
