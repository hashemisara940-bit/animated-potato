package dev.kairo.domain.agent;

import java.util.List;
import java.util.Map;

public record AgentConfig(
        String model,
        String systemPrompt,
        double temperature,
        int maxTokens,
        List<String> enabledTools,
        Map<String, String> providerOptions
) {
    public AgentConfig {
        enabledTools = enabledTools == null ? List.of() : List.copyOf(enabledTools);
        providerOptions = providerOptions == null ? Map.of() : Map.copyOf(providerOptions);
    }

    public static AgentConfig defaults(String model) {
        return new AgentConfig(model, "You are Kairo, a local-first AI agent runtime.", 0.2, 4096, List.of(), Map.of());
    }
}
