package dev.kairo.application.port.in;

import dev.kairo.domain.workspace.Workspace;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkspaceUseCase {
    Mono<Workspace> create(String name, String description);
    Flux<Workspace> list();
}
