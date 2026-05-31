package dev.kairo.api.controller;

import dev.kairo.api.dto.WorkspaceRequest;
import dev.kairo.application.port.in.WorkspaceUseCase;
import dev.kairo.domain.workspace.Workspace;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WorkspaceController {
    private final WorkspaceUseCase useCase;

    public WorkspaceController(WorkspaceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/workspaces")
    public Flux<Workspace> list() {
        return useCase.list();
    }

    @PostMapping("/workspaces")
    public Mono<Workspace> create(@Valid @RequestBody WorkspaceRequest request) {
        return useCase.create(request.name(), request.description());
    }
}
