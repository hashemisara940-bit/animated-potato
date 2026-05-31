package dev.kairo.application.port.in;

import dev.kairo.domain.provider.ProviderDescriptor;
import reactor.core.publisher.Flux;

public interface ListProvidersUseCase {
    Flux<ProviderDescriptor> listProviders();
}
