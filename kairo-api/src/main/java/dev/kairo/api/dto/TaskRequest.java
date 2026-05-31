package dev.kairo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskRequest(@NotNull UUID workspaceId, @NotNull UUID agentId, @NotBlank String objective) {}
