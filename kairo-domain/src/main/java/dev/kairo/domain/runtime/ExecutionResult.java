package dev.kairo.domain.runtime;

import java.util.List;
import java.util.Map;

public record ExecutionResult(boolean success, String output, List<RuntimeEvent> events, Map<String, Object> artifacts) {
    public ExecutionResult {
        events = events == null ? List.of() : List.copyOf(events);
        artifacts = artifacts == null ? Map.of() : Map.copyOf(artifacts);
    }

    public static ExecutionResult success(String output, List<RuntimeEvent> events) { return new ExecutionResult(true, output, events, Map.of()); }
    public static ExecutionResult failure(String output, List<RuntimeEvent> events) { return new ExecutionResult(false, output, events, Map.of()); }
}
