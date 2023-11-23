package org.emmek.bwfinale.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Elemento cod id " + id + " non trovato!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
