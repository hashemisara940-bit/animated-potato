package dev.kairo.application.service;

import dev.kairo.application.port.in.ListProvidersUseCase;
import dev.kairo.application.port.out.ProviderRegistry;
import dev.kairo.domain.provider.ProviderDescriptor;
import reactor.core.publisher.Flux;

public final class ProviderService implements ListProvidersUseCase {
    private final ProviderRegistry providerRegistry;

    public ProviderService(ProviderRegistry providerRegistry) {
        this.providerRegistry = providerRegistry;
    }

    @Override
    public Flux<ProviderDescriptor> listProviders() {
        return Flux.fromIterable(providerRegistry.descriptors());
    }
}
