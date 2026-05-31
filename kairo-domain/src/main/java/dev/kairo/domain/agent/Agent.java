package dev.kairo.domain.agent;

import java.time.Instant;

public record Agent(AgentId id, String name, AgentType type, AgentConfig config, Instant createdAt) {
    public Agent {
        if (id == null || name == null || name.isBlank() || type == null || config == null || createdAt == null) {
            throw new IllegalArgumentException("agent requires id, name, type, config, and creation time");
        }
    }

    public static Agent create(String name, AgentType type, AgentConfig config) {
        return new Agent(AgentId.newId(), name, type, config, Instant.now());
    }
}
