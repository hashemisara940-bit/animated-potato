package dev.kairo.application.service;

import dev.kairo.application.port.in.InvokeToolUseCase;
import dev.kairo.application.port.out.ToolExecutor;
import dev.kairo.application.port.out.ToolRegistry;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class ToolService implements InvokeToolUseCase, ToolExecutor {
    private final ToolRegistry registry;

    public ToolService(ToolRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Mono<ToolResult> invoke(ToolCall call) {
        return execute(call);
    }

    @Override
    public Flux<ToolDefinition> availableTools() {
        return Flux.fromIterable(registry.definitions());
    }

    @Override
    public Mono<ToolResult> execute(ToolCall call) {
        return Mono.fromSupplier(() -> registry.find(call.toolName())
                .map(tool -> tool.execute(call))
                .orElseGet(() -> ToolResult.failure(call.toolName(), "Tool not found: " + call.toolName())));
    }
}
