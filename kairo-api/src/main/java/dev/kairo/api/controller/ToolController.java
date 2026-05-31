package dev.kairo.api.controller;

import dev.kairo.api.dto.ToolExecuteRequest;
import dev.kairo.application.port.in.InvokeToolUseCase;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class ToolController {
    private final InvokeToolUseCase useCase;

    public ToolController(InvokeToolUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/tools")
    public Flux<ToolDefinition> tools() {
        return useCase.availableTools();
    }

    @PostMapping("/tools/execute")
    public Mono<ToolResult> execute(@Valid @RequestBody ToolExecuteRequest request) {
        return useCase.invoke(ToolCall.of(request.toolName(), request.arguments() == null ? Map.of() : request.arguments()));
    }
}
