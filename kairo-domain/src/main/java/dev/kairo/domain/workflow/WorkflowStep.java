package dev.kairo.domain.workflow;

import java.util.Map;
import java.util.UUID;

public record WorkflowStep(UUID id, String name, String action, Map<String, Object> inputs) {
    public WorkflowStep {
        if (id == null || name == null || name.isBlank() || action == null || action.isBlank()) {
            throw new IllegalArgumentException("workflow step requires id, name, and action");
        }
        inputs = inputs == null ? Map.of() : Map.copyOf(inputs);
    }
}
