package dev.kairo.infrastructure.repository;

import dev.kairo.application.port.out.ConversationRepository;
import dev.kairo.domain.conversation.Conversation;
import dev.kairo.domain.conversation.ConversationId;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryConversationRepository implements ConversationRepository {
    private final Map<ConversationId, Conversation> conversations = new ConcurrentHashMap<>();

    @Override
    public Mono<Conversation> save(Conversation conversation) {
        conversations.put(conversation.id(), conversation);
        return Mono.just(conversation);
    }

    @Override
    public Mono<Conversation> findById(ConversationId id) {
        return Mono.justOrEmpty(conversations.get(id));
    }
}
