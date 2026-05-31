package dev.kairo.application.usecase.chat;

import java.util.UUID;

public record ChatCommand(UUID workspaceId, UUID conversationId, String message, String providerId, String model) {}
