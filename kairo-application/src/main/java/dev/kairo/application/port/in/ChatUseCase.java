package dev.kairo.application.port.in;

import dev.kairo.application.usecase.chat.ChatCommand;
import dev.kairo.application.usecase.chat.ChatResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatUseCase {
    Mono<ChatResponse> chat(ChatCommand command);
    Flux<String> stream(ChatCommand command);
}
