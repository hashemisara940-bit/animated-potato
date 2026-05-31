package dev.kairo.infrastructure.tool;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolMetadata;
import dev.kairo.domain.tool.ToolResult;

import java.util.Map;
import java.util.Set;

public final class GitTool implements Tool {
    private final TerminalTool terminalTool = new TerminalTool();

    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("git.exec", "Git", "Runs an allow-listed git command.", Set.of("git", "local"), true), Map.of("args", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        String args = call.arguments().getOrDefault("args", "status --short").toString();
        if (!args.matches("[a-zA-Z0-9_ ./=-]+")) {
            return ToolResult.failure(definition().metadata().name(), "Rejected unsafe git arguments");
        }
        ToolResult result = terminalTool.execute(ToolCall.of("terminal.exec", Map.of("command", "git " + args, "timeoutSeconds", 30)));
        return new ToolResult(definition().metadata().name(), result.success(), result.output(), result.metadata(), result.completedAt());
    }
}
