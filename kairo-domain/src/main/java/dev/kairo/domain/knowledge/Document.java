package dev.kairo.domain.knowledge;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

public record Document(UUID id, UUID knowledgeBaseId, String title, URI source, Instant ingestedAt) {}
