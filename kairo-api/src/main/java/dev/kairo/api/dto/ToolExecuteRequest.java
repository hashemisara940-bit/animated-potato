package dev.kairo.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record ToolExecuteRequest(@NotBlank String toolName, Map<String, Object> arguments) {}
