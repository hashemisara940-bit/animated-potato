package dev.kairo.domain.tool;

public interface Tool {
    ToolDefinition definition();
    ToolResult execute(ToolCall call);
}
