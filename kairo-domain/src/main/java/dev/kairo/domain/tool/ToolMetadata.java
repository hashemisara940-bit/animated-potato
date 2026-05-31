package dev.kairo.domain.tool;

import java.util.Set;

public record ToolMetadata(String name, String displayName, String description, Set<String> tags, boolean destructive) {
    public ToolMetadata {
        if (name == null || name.isBlank() || displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("tool name and display name are required");
        }
        tags = tags == null ? Set.of() : Set.copyOf(tags);
    }
}
