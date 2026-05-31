package dev.kairo.infrastructure.provider;

import dev.kairo.application.port.out.LlmProvider;
import dev.kairo.application.port.out.ProviderRegistry;
import dev.kairo.domain.provider.ProviderCapability;
import dev.kairo.domain.provider.ProviderDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ConfigurableProviderRegistry implements ProviderRegistry {
    private final Map<String, LlmProvider> providers;
    private final String defaultProvider;

    public ConfigurableProviderRegistry(Map<String, LlmProvider> providers, String defaultProvider) {
        this.providers = Map.copyOf(providers);
        this.defaultProvider = defaultProvider;
    }

    @Override
    public Optional<LlmProvider> find(String providerId) {
        return Optional.ofNullable(providers.get(providerId));
    }

    @Override
    public LlmProvider defaultProvider() {
        return find(defaultProvider).orElseGet(() -> providers.values().stream().findFirst().orElseThrow());
    }

    @Override
    public List<ProviderDescriptor> descriptors() {
        List<ProviderDescriptor> descriptors = new ArrayList<>();
        providers.values().forEach(provider -> descriptors.add(provider.descriptor()));
        if (descriptors.isEmpty()) {
            descriptors.add(new ProviderDescriptor("none", "No providers configured", null, java.util.Set.of(ProviderCapability.CHAT)));
        }
        return descriptors;
    }
}
