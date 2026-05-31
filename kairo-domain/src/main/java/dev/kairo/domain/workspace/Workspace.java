package dev.kairo.domain.workspace;

import java.time.Instant;

public record Workspace(WorkspaceId id, String name, String description, WorkspaceSettings settings, Instant createdAt) {
    public Workspace {
        if (id == null || name == null || name.isBlank() || settings == null || createdAt == null) {
            throw new IllegalArgumentException("workspace requires id, name, settings, and creation time");
        }
    }

    public static Workspace create(String name, String description, WorkspaceSettings settings) {
        return new Workspace(WorkspaceId.newId(), name, description, settings == null ? WorkspaceSettings.defaults() : settings, Instant.now());
    }
}
