package dev.kairo.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ChatRequest(UUID workspaceId, UUID conversationId, @NotBlank String message, String providerId, String model, boolean stream) {}
