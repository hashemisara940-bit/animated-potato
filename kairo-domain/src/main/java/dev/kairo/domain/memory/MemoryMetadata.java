package dev.kairo.domain.memory;

import java.util.Map;
import java.util.Set;

public record MemoryMetadata(Set<String> tags, Map<String, Object> attributes) {
    public MemoryMetadata {
        tags = tags == null ? Set.of() : Set.copyOf(tags);
        attributes = attributes == null ? Map.of() : Map.copyOf(attributes);
    }
}
