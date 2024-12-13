package org.example.exoaopbases.exception;

public class NoMatchingBookException extends RuntimeException {
    public NoMatchingBookException() {
        super("Aucun livre ne correspond à cette recherche");
    }

    public NoMatchingBookException(Throwable cause) {
        super(cause);
    }
}
