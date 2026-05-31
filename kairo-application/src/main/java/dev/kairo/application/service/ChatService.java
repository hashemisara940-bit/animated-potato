package dev.kairo.application.service;

import dev.kairo.application.port.in.ChatUseCase;
import dev.kairo.application.port.out.ConversationRepository;
import dev.kairo.application.port.out.LlmProvider;
import dev.kairo.application.port.out.ProviderRegistry;
import dev.kairo.application.usecase.chat.ChatCommand;
import dev.kairo.application.usecase.chat.ChatResponse;
import dev.kairo.domain.agent.AgentConfig;
import dev.kairo.domain.agent.AgentType;
import dev.kairo.domain.agent.Agent;
import dev.kairo.domain.conversation.Conversation;
import dev.kairo.domain.conversation.ConversationId;
import dev.kairo.domain.conversation.Message;
import dev.kairo.domain.conversation.Role;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public final class ChatService implements ChatUseCase {
    private final ProviderRegistry providerRegistry;
    private final ConversationRepository conversationRepository;

    public ChatService(ProviderRegistry providerRegistry, ConversationRepository conversationRepository) {
        this.providerRegistry = providerRegistry;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Mono<ChatResponse> chat(ChatCommand command) {
        return loadOrCreate(command)
                .flatMap(conversation -> {
                    conversation.append(Message.of(Role.USER, command.message()));
                    LlmProvider provider = selectProvider(command.providerId());
                    return provider.chat(conversation.messages(), options(command))
                            .flatMap(reply -> {
                                conversation.append(reply);
                                return conversationRepository.save(conversation)
                                        .thenReturn(new ChatResponse(conversation.id().value(), reply.content()));
                            });
                });
    }

    @Override
    public Flux<String> stream(ChatCommand command) {
        return loadOrCreate(command).flatMapMany(conversation -> {
            conversation.append(Message.of(Role.USER, command.message()));
            return selectProvider(command.providerId()).stream(conversation.messages(), options(command));
        });
    }

    private Mono<Conversation> loadOrCreate(ChatCommand command) {
        if (command.conversationId() != null) {
            return conversationRepository.findById(new ConversationId(command.conversationId()));
        }
        WorkspaceId workspaceId = command.workspaceId() == null ? WorkspaceId.newId() : new WorkspaceId(command.workspaceId());
        Agent agent = Agent.create("default-chat", AgentType.CHAT, AgentConfig.defaults(command.model() == null ? "llama3.1" : command.model()));
        return Mono.just(Conversation.start(workspaceId, agent.id()));
    }

    private LlmProvider selectProvider(String providerId) {
        return providerId == null || providerId.isBlank()
                ? providerRegistry.defaultProvider()
                : providerRegistry.find(providerId).orElseGet(providerRegistry::defaultProvider);
    }

    private Map<String, Object> options(ChatCommand command) {
        return Map.of("model", command.model() == null ? "llama3.1" : command.model());
    }
}
