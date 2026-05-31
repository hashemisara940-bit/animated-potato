package dev.kairo.domain.runtime;

public interface ExecutionEngine {
    ExecutionResult execute(ExecutionContext context);
}
