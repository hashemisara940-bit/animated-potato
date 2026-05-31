package dev.kairo.application.port.out;

import dev.kairo.domain.conversation.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

public interface StreamingProvider {
    Flux<String> stream(List<Message> messages, Map<String, Object> options);
}
