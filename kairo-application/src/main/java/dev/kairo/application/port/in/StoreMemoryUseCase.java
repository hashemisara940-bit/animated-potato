package dev.kairo.application.port.in;

import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreMemoryUseCase {
    Mono<MemoryEntry> store(MemoryEntry entry);
    Flux<MemoryEntry> retrieve(WorkspaceId workspaceId, String query, int limit);
}
