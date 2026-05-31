package dev.kairo.domain.workspace;

import java.util.Map;

public record WorkspaceSettings(String defaultProvider, String defaultModel, Map<String, Object> preferences) {
    public WorkspaceSettings {
        preferences = preferences == null ? Map.of() : Map.copyOf(preferences);
    }

    public static WorkspaceSettings defaults() {
        return new WorkspaceSettings("ollama-local", "llama3.1", Map.of());
    }
}
