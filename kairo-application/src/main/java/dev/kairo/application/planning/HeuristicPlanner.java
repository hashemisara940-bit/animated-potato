package dev.kairo.application.planning;

import dev.kairo.domain.task.ExecutionPlan;
import dev.kairo.domain.task.TaskStep;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public final class HeuristicPlanner implements PlanGenerator, TaskDecomposer {
    @Override
    public Mono<ExecutionPlan> plan(String objective) {
        return Mono.fromSupplier(() -> new ExecutionPlan(objective, decompose(objective)));
    }

    @Override
    public List<TaskStep> decompose(String objective) {
        return List.of(
                TaskStep.planned(1, "Analyze objective and constraints", null, Map.of("objective", objective)),
                TaskStep.planned(2, "Select tools and data sources", null, Map.of()),
                TaskStep.planned(3, "Execute the selected steps", null, Map.of()),
                TaskStep.planned(4, "Evaluate result and summarize", null, Map.of())
        );
    }
}
