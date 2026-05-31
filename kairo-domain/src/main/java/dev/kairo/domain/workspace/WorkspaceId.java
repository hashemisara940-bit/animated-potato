package dev.kairo.domain.workspace;

import java.util.UUID;

public record WorkspaceId(UUID value) {
    public WorkspaceId { if (value == null) throw new IllegalArgumentException("workspace id is required"); }
    public static WorkspaceId newId() { return new WorkspaceId(UUID.randomUUID()); }
}
