package dev.kairo.domain.memory;

import dev.kairo.domain.workspace.WorkspaceId;

import java.time.Instant;
import java.util.UUID;

public record MemoryEntry(UUID id, WorkspaceId workspaceId, MemoryType type, String content, MemoryMetadata metadata, Instant createdAt) {
    public MemoryEntry {
        if (id == null || type == null || content == null || content.isBlank() || createdAt == null) {
            throw new IllegalArgumentException("memory entry requires id, type, content, and creation time");
        }
        metadata = metadata == null ? new MemoryMetadata(null, null) : metadata;
    }

    public static MemoryEntry create(WorkspaceId workspaceId, MemoryType type, String content, MemoryMetadata metadata) {
        return new MemoryEntry(UUID.randomUUID(), workspaceId, type, content, metadata, Instant.now());
    }
}
