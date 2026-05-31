package dev.kairo.application.port.out;

import dev.kairo.domain.conversation.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ChatProvider {
    String id();
    Mono<Message> chat(List<Message> messages, Map<String, Object> options);
    Flux<String> stream(List<Message> messages, Map<String, Object> options);
}
