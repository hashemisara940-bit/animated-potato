package dev.kairo.application.port.out;

import dev.kairo.domain.provider.ProviderDescriptor;

import java.util.List;
import java.util.Optional;

public interface ProviderRegistry {
    Optional<LlmProvider> find(String providerId);
    LlmProvider defaultProvider();
    List<ProviderDescriptor> descriptors();
}
