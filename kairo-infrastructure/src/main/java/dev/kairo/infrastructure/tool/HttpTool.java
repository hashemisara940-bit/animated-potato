package dev.kairo.infrastructure.tool;

import dev.kairo.domain.tool.Tool;
import dev.kairo.domain.tool.ToolCall;
import dev.kairo.domain.tool.ToolDefinition;
import dev.kairo.domain.tool.ToolMetadata;
import dev.kairo.domain.tool.ToolResult;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Set;

public final class HttpTool implements Tool {
    private final WebClient.Builder webClientBuilder;

    public HttpTool(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public ToolDefinition definition() {
        return new ToolDefinition(new ToolMetadata("http.request", "HTTP Request", "Executes a GET request and returns the response body.", Set.of("network", "http"), false), Map.of("url", "string"));
    }

    @Override
    public ToolResult execute(ToolCall call) {
        String url = call.arguments().getOrDefault("url", "").toString();
        try {
            String body = webClientBuilder.build().get().uri(url).retrieve().bodyToMono(String.class).block();
            return ToolResult.success(definition().metadata().name(), body == null ? "" : body);
        } catch (Exception ex) {
            return ToolResult.failure(definition().metadata().name(), ex.getMessage());
        }
    }
}
