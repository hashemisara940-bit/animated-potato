package dev.kairo.domain.knowledge;

import java.util.List;

public interface Retriever {
    List<Chunk> retrieve(String query, int limit);
}
