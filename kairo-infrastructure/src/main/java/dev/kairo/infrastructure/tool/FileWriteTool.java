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

public final class FileWriteTool implements Tool {
    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("file.write", "Write File", "Writes UTF-8 text to a local workspace file.", Set.of("file", "local"), true), Map.of("path", "string", "content", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        Path path = Path.of(call.arguments().getOrDefault("path", "").toString()).normalize();
        String content = call.arguments().getOrDefault("content", "").toString();
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, content);
            return ToolResult.success(definition().metadata().name(), "Wrote " + path);
        } catch (Exception ex) {
            return ToolResult.failure(definition().metadata().name(), ex.getMessage());
        }
    }
}
