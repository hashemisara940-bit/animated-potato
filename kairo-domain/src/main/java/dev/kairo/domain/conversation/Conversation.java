package dev.kairo.domain.conversation;

import dev.kairo.domain.agent.AgentId;
import dev.kairo.domain.workspace.WorkspaceId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Conversation {
    private final ConversationId id;
    private final WorkspaceId workspaceId;
    private final AgentId agentId;
    private final List<Message> messages;
    private ConversationState state;
    private final Instant createdAt;
    private Instant updatedAt;

    private Conversation(ConversationId id, WorkspaceId workspaceId, AgentId agentId, List<Message> messages,
                         ConversationState state, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.agentId = agentId;
        this.messages = new ArrayList<>(messages);
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Conversation start(WorkspaceId workspaceId, AgentId agentId) {
        Instant now = Instant.now();
        return new Conversation(ConversationId.newId(), workspaceId, agentId, List.of(), ConversationState.ACTIVE, now, now);
    }

    public void append(Message message) {
        if (state != ConversationState.ACTIVE) {
            throw new IllegalStateException("conversation is not active");
        }
        messages.add(message);
        updatedAt = Instant.now();
    }

    public ConversationId id() { return id; }
    public WorkspaceId workspaceId() { return workspaceId; }
    public AgentId agentId() { return agentId; }
    public List<Message> messages() { return Collections.unmodifiableList(messages); }
    public ConversationState state() { return state; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }
}
