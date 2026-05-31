package dev.kairo.infrastructure.tool;

import dev.kairo.application.port.out.ToolRegistry;
import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolDefinition;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryToolRegistry implements ToolRegistry {
    private final Map<String, Tool> tools = new ConcurrentHashMap<>();

    @Override
    public void register(Tool tool) {
        tools.put(tool.definition().metadata().name(), tool);
    }

    @Override
    public Optional<Tool> find(String name) {
        return Optional.ofNullable(tools.get(name));
    }

    @Override
    public List<ToolDefinition> definitions() {
        return tools.values().stream()
                .map(Tool::definition)
                .sorted(Comparator.comparing(definition -> definition.metadata().name()))
                .toList();
    }
}
