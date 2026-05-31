package dev.kairo.infrastructure.repository;

import dev.kairo.application.port.out.TaskRepository;
import dev.kairo.domain.task.Task;
import dev.kairo.domain.task.TaskId;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryTaskRepository implements TaskRepository {
    private final Map<TaskId, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public Mono<Task> save(Task task) {
        tasks.put(task.id(), task);
        return Mono.just(task);
    }

    @Override
    public Mono<Task> findById(TaskId id) {
        return Mono.justOrEmpty(tasks.get(id));
    }
}
