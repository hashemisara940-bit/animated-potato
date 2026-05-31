package dev.kairo.domain.task;

import java.util.UUID;

public record TaskId(UUID value) {
    public TaskId { if (value == null) throw new IllegalArgumentException("task id is required"); }
    public static TaskId newId() { return new TaskId(UUID.randomUUID()); }
}
