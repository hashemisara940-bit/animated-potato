package dev.kairo.application.port.out;

import dev.kairo.domain.workspace.Workspace;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkspaceRepository {
    Mono<Workspace> save(Workspace workspace);
    Mono<Workspace> findById(WorkspaceId id);
    Flux<Workspace> findAll();
}
