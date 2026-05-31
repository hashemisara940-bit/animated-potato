package dev.kairo.infrastructure.tool;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolMetadata;
import dev.kairo.domain.tool.ToolResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public final class FileReadTool implements Tool {
    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("file.read", "Read File", "Reads a UTF-8 text file from the local workspace.", Set.of("file", "local"), false), Map.of("path", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        Path path = Path.of(call.arguments().getOrDefault("path", "").toString()).normalize();
        try {
            return ToolResult.success(definition().metadata().name(), Files.readString(path));
        } catch (Exception ex) {
            return ToolResult.failure(definition().metadata().name(), ex.getMessage());
        }
    }
}
