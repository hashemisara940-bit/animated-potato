package dev.kairo.infrastructure.repository;

import dev.kairo.application.port.out.WorkspaceRepository;
import dev.kairo.domain.workspace.Workspace;
import dev.kairo.domain.workspace.WorkspaceId;
import dev.kairo.domain.workspace.WorkspaceSettings;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryWorkspaceRepository implements WorkspaceRepository {
    private final Map<WorkspaceId, Workspace> workspaces = new ConcurrentHashMap<>();

    public InMemoryWorkspaceRepository() {
        Workspace workspace = Workspace.create("Default Workspace", "Local-first personal agent workspace", WorkspaceSettings.defaults());
        workspaces.put(workspace.id(), workspace);
    }

    @Override
    public Mono<Workspace> save(Workspace workspace) {
        workspaces.put(workspace.id(), workspace);
        return Mono.just(workspace);
    }

    @Override
    public Mono<Workspace> findById(WorkspaceId id) {
        return Mono.justOrEmpty(workspaces.get(id));
    }

    @Override
    public Flux<Workspace> findAll() {
        return Flux.fromIterable(workspaces.values());
    }
}
