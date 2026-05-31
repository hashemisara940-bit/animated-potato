package dev.kairo.application.service;

import dev.kairo.domain.runtime.AgentRuntime;
import dev.kairo.domain.runtime.ExecutionContext;
import dev.kairo.domain.runtime.ExecutionEngine;
import dev.kairo.domain.runtime.ExecutionResult;

public final class SimpleAgentRuntime implements AgentRuntime {
    private final ExecutionEngine executionEngine;

    public SimpleAgentRuntime(ExecutionEngine executionEngine) {
        this.executionEngine = executionEngine;
    }

    @Override
    public ExecutionResult run(ExecutionContext context) {
        return executionEngine.execute(context);
    }
}
