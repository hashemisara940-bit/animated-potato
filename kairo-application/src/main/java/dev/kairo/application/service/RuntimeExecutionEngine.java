package dev.kairo.application.service;

import dev.kairo.domain.runtime.ExecutionContext;
import dev.kairo.domain.runtime.ExecutionEngine;
import dev.kairo.domain.runtime.ExecutionResult;
import dev.kairo.domain.runtime.RuntimeEvent;

import java.util.List;

public final class RuntimeExecutionEngine implements ExecutionEngine {
    @Override
    public ExecutionResult execute(ExecutionContext context) {
        return ExecutionResult.success(
                "Runtime accepted objective: " + context.objective(),
                List.of(
                        RuntimeEvent.of("runtime.started", "Agent runtime started"),
                        RuntimeEvent.of("planner.ready", "Planner extension point is available"),
                        RuntimeEvent.of("runtime.completed", "Agent runtime completed")
                )
        );
    }
}
