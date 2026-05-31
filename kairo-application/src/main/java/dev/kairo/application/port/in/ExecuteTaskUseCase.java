package dev.kairo.application.port.in;

import dev.kairo.application.usecase.task.ExecuteTaskCommand;
import dev.kairo.domain.task.Task;
import dev.kairo.domain.task.TaskId;
import reactor.core.publisher.Mono;

public interface ExecuteTaskUseCase {
    Mono<Task> execute(ExecuteTaskCommand command);
    Mono<Task> find(TaskId taskId);
}
