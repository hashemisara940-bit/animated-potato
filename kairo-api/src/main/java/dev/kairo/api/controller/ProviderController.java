package dev.kairo.api.controller;

import dev.kairo.application.port.in.ListProvidersUseCase;
import dev.kairo.domain.provider.ProviderDescriptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProviderController {
    private final ListProvidersUseCase useCase;

    public ProviderController(ListProvidersUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/providers")
    public Flux<ProviderDescriptor> list() {
        return useCase.listProviders();
    }
}
