package dev.kairo.application.port.out;

import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemoryRepository {
    Mono<MemoryEntry> save(MemoryEntry entry);
    Flux<MemoryEntry> findRelevant(WorkspaceId workspaceId, String query, int limit);
}
