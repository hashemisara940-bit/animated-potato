package dev.kairo.application.usecase.task;

import java.util.UUID;

public record ExecuteTaskCommand(UUID workspaceId, UUID agentId, String objective) {}
