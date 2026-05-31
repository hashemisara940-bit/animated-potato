package dev.kairo.infrastructure.provider;

import dev.kairo.application.port.out.LlmProvider;
import dev.kairo.domain.conversation.Message;
import dev.kairo.domain.conversation.Role;
import dev.kairo.domain.provider.ProviderCapability;
import dev.kairo.domain.provider.ProviderDescriptor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class OllamaChatProvider implements LlmProvider {
    private final String id;
    private final String host;
    private final WebClient webClient;

    public OllamaChatProvider(String id, String host, WebClient.Builder webClientBuilder) {
        this.id = id;
        this.host = host;
        this.webClient = webClientBuilder.baseUrl(host).build();
    }

    @Override
    public String id() { return id; }

    @Override
    public ProviderDescriptor descriptor() {
        return new ProviderDescriptor(id, "Ollama", host, Set.of(ProviderCapability.CHAT, ProviderCapability.STREAMING));
    }

    @Override
    public Mono<Message> chat(List<Message> messages, Map<String, Object> options) {
        return webClient.post()
                .uri("/api/chat")
                .bodyValue(Map.of("model", options.getOrDefault("model", "llama3.1"), "stream", false, "messages", toOllamaMessages(messages)))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> new Message(UUID.randomUUID(), Role.ASSISTANT, extractContent(response), Instant.now(), Map.of("provider", id)));
    }

    @Override
    public Flux<String> stream(List<Message> messages, Map<String, Object> options) {
        return webClient.post()
                .uri("/api/chat")
                .bodyValue(Map.of("model", options.getOrDefault("model", "llama3.1"), "stream", true, "messages", toOllamaMessages(messages)))
                .retrieve()
                .bodyToFlux(Map.class)
                .map(this::extractContent);
    }

    private List<Map<String, String>> toOllamaMessages(List<Message> messages) {
        return messages.stream()
                .map(message -> Map.of("role", message.role().name().toLowerCase(), "content", message.content()))
                .toList();
    }

    private String extractContent(Map<?, ?> response) {
        Object message = response.get("message");
        if (message instanceof Map<?, ?> messageMap) {
            Object content = messageMap.get("content");
            return content == null ? "" : content.toString();
        }
        Object responseText = response.get("response");
        return responseText == null ? "" : responseText.toString();
    }
}
