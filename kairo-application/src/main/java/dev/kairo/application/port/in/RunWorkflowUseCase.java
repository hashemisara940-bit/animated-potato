package dev.kairo.application.port.in;

import dev.kairo.domain.workflow.Workflow;
import reactor.core.publisher.Mono;

public interface RunWorkflowUseCase {
    Mono<Workflow> run(Workflow workflow);
}
