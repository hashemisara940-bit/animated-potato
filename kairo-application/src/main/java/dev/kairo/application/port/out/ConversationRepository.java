package dev.kairo.application.port.out;

import dev.kairo.domain.conversation.Conversation;
import dev.kairo.domain.conversation.ConversationId;
import reactor.core.publisher.Mono;

public interface ConversationRepository {
    Mono<Conversation> save(Conversation conversation);
    Mono<Conversation> findById(ConversationId id);
}
