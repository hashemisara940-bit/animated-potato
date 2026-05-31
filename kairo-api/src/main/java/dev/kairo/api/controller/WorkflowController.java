package dev.kairo.api.controller;

import dev.kairo.domain.workflow.Workflow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WorkflowController {
    @PostMapping("/workflows")
    public Mono<Workflow> create(@RequestBody Workflow workflow) {
        return Mono.just(workflow);
    }
}
