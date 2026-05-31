package dev.kairo.infrastructure.tool;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolMetadata;
import dev.kairo.domain.tool.ToolResult;

import java.util.Map;
import java.util.Set;

public final class DatabaseTool implements Tool {
    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("database.query", "Database Query", "Placeholder for read-only database inspection through configured adapters.", Set.of("database"), false), Map.of("sql", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        return ToolResult.failure(definition().metadata().name(), "Database query adapter is intentionally disabled until a safe read-only policy is configured.");
    }
}
