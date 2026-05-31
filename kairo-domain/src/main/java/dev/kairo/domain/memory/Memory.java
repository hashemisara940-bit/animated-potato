package dev.kairo.domain.memory;

import dev.kairo.domain.workspace.WorkspaceId;

import java.util.List;

public record Memory(WorkspaceId workspaceId, List<MemoryEntry> entries) {
    public Memory {
        entries = entries == null ? List.of() : List.copyOf(entries);
    }
}
