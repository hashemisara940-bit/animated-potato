package dev.kairo.domain.knowledge;

import dev.kairo.domain.workspace.WorkspaceId;

import java.util.UUID;

public record KnowledgeBase(UUID id, WorkspaceId workspaceId, String name) {}
