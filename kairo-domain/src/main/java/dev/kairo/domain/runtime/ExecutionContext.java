package dev.kairo.domain.runtime;

import dev.kairo.domain.agent.Agent;
import dev.kairo.domain.conversation.Message;
import dev.kairo.domain.memory.MemoryEntry;
import dev.kairo.domain.task.ExecutionPlan;
import dev.kairo.domain.workspace.WorkspaceId;

import java.util.List;
import java.util.Map;

public record ExecutionContext(WorkspaceId workspaceId, Agent agent, String objective, List<Message> conversation,
                               List<MemoryEntry> memories, ExecutionPlan plan, Map<String, Object> variables) {
    public ExecutionContext {
        conversation = conversation == null ? List.of() : List.copyOf(conversation);
        memories = memories == null ? List.of() : List.copyOf(memories);
        variables = variables == null ? Map.of() : Map.copyOf(variables);
    }
}
