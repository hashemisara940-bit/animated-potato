package dev.kairo.infrastructure.provider;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "kairo.providers")
public record KairoProviderProperties(String defaultProvider, Map<String, ProviderConfig> configured) {
    public KairoProviderProperties {
        defaultProvider = defaultProvider == null ? "ollama-local" : defaultProvider;
        configured = configured == null ? defaults() : new LinkedHashMap<>(configured);
    }

    private static Map<String, ProviderConfig> defaults() {
        return Map.of(
                "ollama-local", new ProviderConfig("ollama", "http://localhost:11434", null),
                "ollama-remote", new ProviderConfig("ollama", "http://192.168.1.100:11434", null),
                "openai", new ProviderConfig("openai", "https://api.openai.com", null),
                "anthropic", new ProviderConfig("anthropic", "https://api.anthropic.com", null),
                "gemini", new ProviderConfig("gemini", "https://generativelanguage.googleapis.com", null),
                "openrouter", new ProviderConfig("openrouter", "https://openrouter.ai/api", null)
        );
    }

    public record ProviderConfig(String type, String host, String apiKey) {}
}
