package dev.kairo.domain.multiagent;

import dev.kairo.domain.agent.Agent;
import dev.kairo.domain.agent.AgentId;

import java.util.Optional;

public interface AgentRegistry {
    Optional<Agent> findById(AgentId id);
    void register(Agent agent);
}
