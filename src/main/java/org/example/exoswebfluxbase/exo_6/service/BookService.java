package org.example.exoswebfluxbase.exo_6.service;

import org.example.exoswebfluxbase.exo_6.entity.Book;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BookService {
    private final Map<String, Book> books;


    public BookService() {
        books = new HashMap<>();
    }

    public Flux<Book> getBooks() {
        return Flux.fromIterable(books.values());
    }

    public Flux<Book> searchByTitle(String title) {
        return Flux.fromIterable(books.values())
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()));
    }

    public Mono<Book> addBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
        return Mono.just(book);
    }

    public Mono<Boolean> removeBook(String id) {
        if (books.containsKey(id)) {
            books.remove(id);
            return Mono.just(true);
        }
        return Mono.just(false);
    }
}
