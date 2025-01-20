package org.example.exoswebfluxbase.exo_6.handler;

import org.example.exoswebfluxbase.exo_6.entity.Book;
import org.example.exoswebfluxbase.exo_6.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class BookHandler {
    @Autowired
    private BookService bookService;

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        return ok().body(bookService.getBooks(), Book.class);
    }

    public Mono<ServerResponse> search(ServerRequest request) {
        String searchedTitle = request.queryParam("title").orElse("");
        return ok().body(bookService.searchByTitle(searchedTitle), Book.class);
    }

    public Mono<ServerResponse> addBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(bookService::addBook)
                .flatMap(book -> created(request.uri()).bodyValue(book));
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        String id = request.pathVariable("id");
        return bookService.removeBook(id)
                .flatMap(result -> {
                    if (result) {
                        return ok().build();
                    }
                    return notFound().build();
                });
    }
}
