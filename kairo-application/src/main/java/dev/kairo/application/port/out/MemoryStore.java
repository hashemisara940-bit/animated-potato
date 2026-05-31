package dev.kairo.application.port.out;

import dev.kairo.domain.memory.MemoryEntry;
import reactor.core.publisher.Mono;

public interface MemoryStore {
    Mono<MemoryEntry> store(MemoryEntry entry);
}
