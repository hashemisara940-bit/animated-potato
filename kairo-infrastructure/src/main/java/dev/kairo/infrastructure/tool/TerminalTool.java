package dev.kairo.infrastructure.tool;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolMetadata;
import dev.kairo.domain.tool.ToolResult;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class TerminalTool implements Tool {
    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("terminal.exec", "Terminal", "Runs a local shell command with timeout protection.", Set.of("terminal", "local"), true), Map.of("command", "string", "timeoutSeconds", "number"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        String command = call.arguments().getOrDefault("command", "").toString();
        long timeoutSeconds = Long.parseLong(call.arguments().getOrDefault("timeoutSeconds", "30").toString());
        try {
            Process process = new ProcessBuilder("bash", "-lc", command).redirectErrorStream(true).start();
            boolean completed = process.waitFor(Duration.ofSeconds(timeoutSeconds).toSeconds(), TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                return ToolResult.failure(definition().metadata().name(), "Command timed out");
            }
            String output = new String(process.getInputStream().readAllBytes());
            return process.exitValue() == 0 ? ToolResult.success(definition().metadata().name(), output) : ToolResult.failure(definition().metadata().name(), output);
        } catch (Exception ex) {
            return ToolResult.failure(definition().metadata().name(), ex.getMessage());
        }
    }
}
