package dev.kairo.domain.multiagent;

import dev.kairo.domain.runtime.ExecutionContext;
import dev.kairo.domain.runtime.ExecutionResult;

public interface AgentCoordinator {
    ExecutionResult coordinate(ExecutionContext context);
}
