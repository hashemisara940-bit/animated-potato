package dev.kairo.domain.agent;

import java.util.UUID;

public record AgentId(UUID value) {
    public AgentId {
        if (value == null) {
            throw new IllegalArgumentException("agent id is required");
        }
    }

    public static AgentId newId() {
        return new AgentId(UUID.randomUUID());
    }
}
