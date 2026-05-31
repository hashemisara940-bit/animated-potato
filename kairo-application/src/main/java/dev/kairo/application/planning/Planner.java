package dev.kairo.application.planning;

import dev.kairo.domain.task.ExecutionPlan;
import reactor.core.publisher.Mono;

public interface Planner {
    Mono<ExecutionPlan> plan(String objective);
}
