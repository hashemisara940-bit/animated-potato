package dev.kairo.application.planning;

import dev.kairo.domain.task.ExecutionPlan;
import dev.kairo.domain.tool.ToolResult;

import java.util.List;

public interface PlanEvaluator {
    boolean isComplete(ExecutionPlan plan, List<ToolResult> results);
}
