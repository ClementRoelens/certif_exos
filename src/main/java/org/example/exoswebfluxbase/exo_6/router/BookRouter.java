package org.example.exoswebfluxbase.exo_6.router;

import org.example.exoswebfluxbase.exo_6.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandler) {
        final String ROOT = "/api/books";

        return route(GET(ROOT),bookHandler::getBooks)
                .andRoute(GET(ROOT+"/search"), bookHandler::search)
                .andRoute(POST(ROOT), bookHandler::addBook)
                .andRoute(DELETE(ROOT+"/{id}"), bookHandler::deleteBook);
    }
}
