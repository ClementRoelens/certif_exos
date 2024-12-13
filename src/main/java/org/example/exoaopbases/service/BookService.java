package org.example.exoaopbases.service;

import org.example.exoaopbases.annotation.ExceptionAnnotation;
import org.example.exoaopbases.annotation.LogAnnotation;
import org.example.exoaopbases.annotation.PerformanceAnnotation;
import org.example.exoaopbases.annotation.ReturningLogAnnotation;
import org.example.exoaopbases.exception.NoMatchingBookException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class BookService {
    private static final Random random = new Random();

    @ExceptionAnnotation
    @ReturningLogAnnotation
    public List<String> findBooks(){
        try {
            Thread.sleep(random.nextInt(2000));
            if (random.nextBoolean()) {
                throw new NoMatchingBookException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList("Livre 1", "Livre 2", "Livre 3");
    }

    @PerformanceAnnotation
    @LogAnnotation
    public void createBook(String book){
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Livre créé");
    }

    @PerformanceAnnotation
    @LogAnnotation
    public void deleteBook(){
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Livre supprimé");
    }
}
