package org.emmek.bwfinale.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super("Elemento cod id " + id + " non trovato!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
