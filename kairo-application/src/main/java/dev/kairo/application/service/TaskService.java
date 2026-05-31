package dev.kairo.application.service;

import dev.kairo.application.planning.Planner;
import dev.kairo.application.port.in.ExecuteTaskUseCase;
import dev.kairo.application.port.out.TaskRepository;
import dev.kairo.application.usecase.task.ExecuteTaskCommand;
import dev.kairo.domain.agent.AgentId;
import dev.kairo.domain.task.Task;
import dev.kairo.domain.task.TaskId;
import dev.kairo.domain.workspace.WorkspaceId;
import reactor.core.publisher.Mono;

public final class TaskService implements ExecuteTaskUseCase {
    private final TaskRepository taskRepository;
    private final Planner planner;

    public TaskService(TaskRepository taskRepository, Planner planner) {
        this.taskRepository = taskRepository;
        this.planner = planner;
    }

    @Override
    public Mono<Task> execute(ExecuteTaskCommand command) {
        Task task = Task.create(new WorkspaceId(command.workspaceId()), new AgentId(command.agentId()), command.objective());
        return planner.plan(command.objective())
                .map(task::withPlan)
                .map(Task::running)
                .flatMap(taskRepository::save);
    }

    @Override
    public Mono<Task> find(TaskId taskId) {
        return taskRepository.findById(taskId);
    }
}
