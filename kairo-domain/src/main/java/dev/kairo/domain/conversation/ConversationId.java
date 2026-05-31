package dev.kairo.domain.conversation;

import java.util.UUID;

public record ConversationId(UUID value) {
    public ConversationId {
        if (value == null) {
            throw new IllegalArgumentException("conversation id is required");
        }
    }

    public static ConversationId newId() {
        return new ConversationId(UUID.randomUUID());
    }
}
