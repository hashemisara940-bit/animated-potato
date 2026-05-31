package dev.kairo.domain.provider;

import java.util.Set;

public record ProviderDescriptor(String id, String name, String host, Set<ProviderCapability> capabilities) {
    public ProviderDescriptor {
        if (id == null || id.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("provider id and name are required");
        }
        capabilities = capabilities == null ? Set.of() : Set.copyOf(capabilities);
    }
}
