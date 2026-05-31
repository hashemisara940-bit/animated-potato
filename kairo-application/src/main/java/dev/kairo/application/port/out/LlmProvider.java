package dev.kairo.application.port.out;

import dev.kairo.domain.provider.ProviderDescriptor;

public interface LlmProvider extends ChatProvider {
    ProviderDescriptor descriptor();
}
