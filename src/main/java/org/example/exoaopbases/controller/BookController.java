package org.example.exoaopbases.controller;

import org.example.exoaopbases.exception.NoMatchingBookException;
import org.example.exoaopbases.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("find")
    public List<String> findBooks(){
        try {
            return bookService.findBooks();
        } catch (NoMatchingBookException e){
            return null;
        }
    }
    @GetMapping("create")
    public void createBook(){
        bookService.createBook("Livre très original");
    }

    @GetMapping("delete")
    public void deleteBook(){
        bookService.deleteBook();
    }
}
