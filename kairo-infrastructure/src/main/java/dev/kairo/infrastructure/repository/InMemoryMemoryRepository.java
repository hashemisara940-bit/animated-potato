package dev.kairo.infrastructure.repository;

import dev.kairo.application.port.out.MemoryRepository;
import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class InMemoryMemoryRepository implements MemoryRepository {
    private final List<MemoryEntry> entries = new CopyOnWriteArrayList<>();

    @Override
    public Mono<MemoryEntry> save(MemoryEntry entry) {
        entries.add(entry);
        return Mono.just(entry);
    }

    @Override
    public Flux<MemoryEntry> findRelevant(WorkspaceId workspaceId, String query, int limit) {
        String normalized = query == null ? "" : query.toLowerCase();
        return Flux.fromIterable(entries)
                .filter(entry -> workspaceId == null || workspaceId.equals(entry.workspaceId()))
                .filter(entry -> normalized.isBlank() || entry.content().toLowerCase().contains(normalized))
                .take(limit <= 0 ? 10 : limit);
    }
}
