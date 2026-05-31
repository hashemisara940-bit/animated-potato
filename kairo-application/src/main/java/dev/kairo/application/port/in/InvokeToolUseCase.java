package dev.kairo.application.port.in;

import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvokeToolUseCase {
    Mono<ToolResult> invoke(ToolCall call);
    Flux<ToolDefinition> availableTools();
}
