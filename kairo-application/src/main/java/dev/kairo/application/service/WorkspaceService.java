package dev.kairo.application.service;

import dev.kairo.application.port.in.WorkspaceUseCase;
import dev.kairo.application.port.out.WorkspaceRepository;
import dev.kairo.domain.workspace.Workspace;
import dev.kairo.domain.workspace.WorkspaceSettings;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class WorkspaceService implements WorkspaceUseCase {
    private final WorkspaceRepository repository;

    public WorkspaceService(WorkspaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Workspace> create(String name, String description) {
        return repository.save(Workspace.create(name, description, WorkspaceSettings.defaults()));
    }

    @Override
    public Flux<Workspace> list() {
        return repository.findAll();
    }
}
