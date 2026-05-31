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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DirectorySearchTool implements Tool {
    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("directory.search", "Directory Search", "Lists files beneath a directory with an optional glob.", Set.of("file", "search"), false), Map.of("path", "string", "glob", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        Path root = Path.of(call.arguments().getOrDefault("path", ".").toString()).normalize();
        String glob = call.arguments().getOrDefault("glob", "*").toString();
        try (Stream<Path> paths = Files.find(root, 20, (path, attributes) -> path.getFileName().toString().matches(glob.replace("*", ".*")))) {
            return ToolResult.success(definition().metadata().name(), paths.map(Path::toString).collect(Collectors.joining("\n")));
        } catch (Exception ex) {
            return ToolResult.failure(definition().metadata().name(), ex.getMessage());
        }
    }
}
