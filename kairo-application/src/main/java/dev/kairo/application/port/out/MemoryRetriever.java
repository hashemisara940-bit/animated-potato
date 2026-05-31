package dev.kairo.application.port.out;

import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;

public interface MemoryRetriever {
    Flux<MemoryEntry> retrieve(WorkspaceId workspaceId, String query, int limit);
}
