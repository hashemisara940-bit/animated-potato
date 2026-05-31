package dev.kairo.domain.task;

import java.util.List;

public record ExecutionPlan(String objective, List<TaskStep> steps) {
    public ExecutionPlan {
        if (objective == null || objective.isBlank()) throw new IllegalArgumentException("objective is required");
        steps = steps == null ? List.of() : List.copyOf(steps);
    }
}
