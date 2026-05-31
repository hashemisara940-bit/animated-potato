package dev.kairo.api.dto;

import jakarta.validation.constraints.NotBlank;

public record WorkspaceRequest(@NotBlank String name, String description) {}
