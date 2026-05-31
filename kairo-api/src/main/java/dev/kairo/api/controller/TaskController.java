package dev.kairo.api.controller;

import dev.kairo.api.dto.TaskRequest;
import dev.kairo.application.port.in.ExecuteTaskUseCase;
import dev.kairo.application.usecase.task.ExecuteTaskCommand;
import dev.kairo.domain.task.Task;
import dev.kairo.domain.task.TaskId;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class TaskController {
    private final ExecuteTaskUseCase useCase;

    public TaskController(ExecuteTaskUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/tasks")
    public Mono<Task> create(@Valid @RequestBody TaskRequest request) {
        return useCase.execute(new ExecuteTaskCommand(request.workspaceId(), request.agentId(), request.objective()));
    }

    @GetMapping("/tasks/{id}")
    public Mono<Task> get(@PathVariable UUID id) {
        return useCase.find(new TaskId(id));
    }
}
