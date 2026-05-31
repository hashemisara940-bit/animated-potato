package dev.kairo.application.planning;

import dev.kairo.domain.task.TaskStep;

import java.util.List;

public interface TaskDecomposer {
    List<TaskStep> decompose(String objective);
}
