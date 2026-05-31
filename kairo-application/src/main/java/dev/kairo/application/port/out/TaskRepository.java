package dev.kairo.application.port.out;

import dev.kairo.domain.task.Task;
import dev.kairo.domain.task.TaskId;
import reactor.core.publisher.Mono;

public interface TaskRepository {
    Mono<Task> save(Task task);
    Mono<Task> findById(TaskId id);
}
