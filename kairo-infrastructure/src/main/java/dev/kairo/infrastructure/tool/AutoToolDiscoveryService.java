package dev.kairo.infrastructure.tool;

import dev.kairo.application.port.out.ToolDiscoveryService;
import dev.kairo.application.port.out.ToolRegistry;
import dev.kairo.domain.tool.Tool;

import java.util.List;

public final class AutoToolDiscoveryService implements ToolDiscoveryService {
    private final ToolRegistry registry;
    private final List<Tool> tools;

    public AutoToolDiscoveryService(ToolRegistry registry, List<Tool> tools) {
        this.registry = registry;
        this.tools = tools;
    }

    @Override
    public void discover() {
        tools.forEach(registry::register);
    }
}
