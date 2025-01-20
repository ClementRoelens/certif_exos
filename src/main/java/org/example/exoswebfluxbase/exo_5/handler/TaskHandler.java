package org.example.exoswebfluxbase.exo_5.handler;

import org.example.exoswebfluxbase.exo_5.entity.Task;
import org.example.exoswebfluxbase.exo_5.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Component
public class TaskHandler {
    @Autowired
    private TaskService taskService;

    public Mono<ServerResponse> getTasks(ServerRequest request) {
        return ok().body(taskService.getTasks(), Task.class);
    }

    public Mono<ServerResponse> getTaskById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return taskService.getTaskById(id)
                .flatMap(task -> ok().bodyValue(task))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> addTask(ServerRequest request) {
        return request.bodyToMono(Task.class)
                .flatMap(taskService::createTask)
                .flatMap(task -> created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> updateTask(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return request.bodyToMono(Task.class)
                .flatMap(task -> taskService.updateTask(id, task))
                .flatMap(task -> ok().bodyValue(task))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> deleteTask(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return taskService.deleteTask(id)
                .flatMap(result -> {
                    if (result) {
                        return ok().build();
                    }
                    return notFound().build();
                });
    }
}
