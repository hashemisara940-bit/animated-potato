package dev.kairo.api.controller;

import dev.kairo.api.dto.ChatRequest;
import dev.kairo.application.port.in.ChatUseCase;
import dev.kairo.application.usecase.chat.ChatCommand;
import dev.kairo.application.usecase.chat.ChatResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ChatController {
    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @PostMapping(path = "/chat")
    public Mono<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        return chatUseCase.chat(toCommand(request));
    }

    @PostMapping(path = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@Valid @RequestBody ChatRequest request) {
        return chatUseCase.stream(toCommand(request));
    }

    private ChatCommand toCommand(ChatRequest request) {
        return new ChatCommand(request.workspaceId(), request.conversationId(), request.message(), request.providerId(), request.model());
    }
}
