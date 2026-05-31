package dev.kairo.domain.task;

import java.util.Map;
import java.util.UUID;

public record TaskStep(UUID id, int orderIndex, String objective, String toolName, Map<String, Object> inputs, TaskStatus status) {
    public TaskStep {
        if (id == null || objective == null || objective.isBlank()) throw new IllegalArgumentException("step id and objective are required");
        inputs = inputs == null ? Map.of() : Map.copyOf(inputs);
    }

    public static TaskStep planned(int orderIndex, String objective, String toolName, Map<String, Object> inputs) {
        return new TaskStep(UUID.randomUUID(), orderIndex, objective, toolName, inputs, TaskStatus.PENDING);
    }
}
