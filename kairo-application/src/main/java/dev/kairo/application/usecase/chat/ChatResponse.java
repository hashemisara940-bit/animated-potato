package dev.kairo.application.usecase.chat;

import java.util.UUID;

public record ChatResponse(UUID conversationId, String message) {}
