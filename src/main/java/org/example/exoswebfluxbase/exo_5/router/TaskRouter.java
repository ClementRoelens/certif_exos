package org.example.exoswebfluxbase.exo_5.router;

import org.example.exoswebfluxbase.exo_5.handler.TaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TaskRouter {

    @Bean
    public RouterFunction<ServerResponse> taskRoutes(TaskHandler taskHandler) {
        final String ROOT = "/api/tasks";

        return route(GET(ROOT), taskHandler::getTasks)
                .andRoute(GET(ROOT + "/{id}"), taskHandler::getTaskById)
                .andRoute(POST(ROOT), taskHandler::addTask)
                .andRoute(PUT(ROOT + "/{id}"), taskHandler::updateTask)
                .andRoute(DELETE(ROOT + "/{id}"), taskHandler::deleteTask);
    }
}
