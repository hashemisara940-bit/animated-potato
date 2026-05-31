package dev.kairo.domain.task;

import dev.kairo.domain.agent.AgentId;
import dev.kairo.domain.workspace.WorkspaceId;

import java.time.Instant;

public record Task(TaskId id, WorkspaceId workspaceId, AgentId agentId, String objective, TaskStatus status,
                   ExecutionPlan plan, Instant createdAt, Instant updatedAt) {
    public Task {
        if (id == null || objective == null || objective.isBlank() || status == null || createdAt == null || updatedAt == null) {
            throw new IllegalArgumentException("task requires id, objective, status, and timestamps");
        }
    }

    public static Task create(WorkspaceId workspaceId, AgentId agentId, String objective) {
        Instant now = Instant.now();
        return new Task(TaskId.newId(), workspaceId, agentId, objective, TaskStatus.PENDING, null, now, now);
    }

    public Task withPlan(ExecutionPlan plan) {
        return new Task(id, workspaceId, agentId, objective, TaskStatus.PLANNING, plan, createdAt, Instant.now());
    }

    public Task running() { return transition(TaskStatus.RUNNING); }
    public Task succeeded() { return transition(TaskStatus.SUCCEEDED); }
    public Task failed() { return transition(TaskStatus.FAILED); }

    private Task transition(TaskStatus next) {
        return new Task(id, workspaceId, agentId, objective, next, plan, createdAt, Instant.now());
    }
}
