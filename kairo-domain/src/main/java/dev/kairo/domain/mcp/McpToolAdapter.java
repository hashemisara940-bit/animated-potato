package dev.kairo.domain.mcp;

import dev.kairo.domain.tool.Tool;

public interface McpToolAdapter {
    Tool adapt(String serverToolName);
}
