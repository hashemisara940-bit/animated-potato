package dev.kairo.domain.multiagent;

import dev.kairo.domain.runtime.RuntimeEvent;

public interface AgentCommunicationBus {
    void publish(RuntimeEvent event);
}
