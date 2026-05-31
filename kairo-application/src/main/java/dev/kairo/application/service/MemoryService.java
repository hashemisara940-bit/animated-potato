package dev.kairo.application.service;

import dev.kairo.application.port.in.StoreMemoryUseCase;
import dev.kairo.application.port.out.MemoryRepository;
import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class MemoryService implements StoreMemoryUseCase {
    private final MemoryRepository repository;

    public MemoryService(MemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<MemoryEntry> store(MemoryEntry entry) {
        return repository.save(entry);
    }

    @Override
    public Flux<MemoryEntry> retrieve(WorkspaceId workspaceId, String query, int limit) {
        return repository.findRelevant(workspaceId, query, limit);
    }
}
