package dev.kairo.infrastructure.provider;

import dev.kairo.application.port.out.LlmProvider;
import dev.kairo.domain.conversation.Message;
import dev.kairo.domain.conversation.Role;
import dev.kairo.domain.provider.ProviderCapability;
import dev.kairo.domain.provider.ProviderDescriptor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class RemoteHttpChatProvider implements LlmProvider {
    private final ProviderDescriptor descriptor;

    public RemoteHttpChatProvider(String id, String displayName, String host) {
        this.descriptor = new ProviderDescriptor(id, displayName, host, Set.of(ProviderCapability.CHAT, ProviderCapability.STREAMING, ProviderCapability.TOOL_CALLING));
    }

    @Override
    public String id() { return descriptor.id(); }

    @Override
    public ProviderDescriptor descriptor() { return descriptor; }

    @Override
    public Mono<Message> chat(List<Message> messages, Map<String, Object> options) {
        String note = descriptor.name() + " adapter is configured but network request mapping is intentionally provider-specific and ready to extend.";
        return Mono.just(new Message(UUID.randomUUID(), Role.ASSISTANT, note, Instant.now(), Map.of("provider", id())));
    }

    @Override
    public Flux<String> stream(List<Message> messages, Map<String, Object> options) {
        return Flux.just(descriptor.name(), " streaming adapter ", "is ready to implement.");
    }
}
