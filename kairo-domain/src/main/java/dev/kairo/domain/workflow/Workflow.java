package dev.kairo.domain.workflow;

import dev.kairo.domain.workspace.WorkspaceId;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Workflow(UUID id, WorkspaceId workspaceId, String name, WorkflowStatus status, List<WorkflowStep> steps, Instant createdAt) {
    public Workflow {
        if (id == null || name == null || name.isBlank() || status == null || createdAt == null) {
            throw new IllegalArgumentException("workflow requires id, name, status, and creation time");
        }
        steps = steps == null ? List.of() : List.copyOf(steps);
    }

    public static Workflow draft(WorkspaceId workspaceId, String name, List<WorkflowStep> steps) {
        return new Workflow(UUID.randomUUID(), workspaceId, name, WorkflowStatus.DRAFT, steps, Instant.now());
    }
}
