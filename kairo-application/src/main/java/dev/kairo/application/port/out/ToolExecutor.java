package dev.kairo.application.port.out;

import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolResult;
import reactor.core.publisher.Mono;

public interface ToolExecutor {
    Mono<ToolResult> execute(ToolCall call);
}
