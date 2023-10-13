package com.maac.springboottutorial.error.exception;

public class DuplicateBookException extends Exception {

    public DuplicateBookException() {
        super();
    }

    public DuplicateBookException(String message) {
        super(message);
    }

    public DuplicateBookException(Throwable cause) {
        super(cause);
    }

    public DuplicateBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
