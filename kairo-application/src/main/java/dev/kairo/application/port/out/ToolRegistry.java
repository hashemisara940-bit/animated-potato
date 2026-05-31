package dev.kairo.application.port.out;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolDefinition;

import java.util.List;
import java.util.Optional;

public interface ToolRegistry {
    void register(Tool tool);
    Optional<Tool> find(String name);
    List<ToolDefinition> definitions();
}
