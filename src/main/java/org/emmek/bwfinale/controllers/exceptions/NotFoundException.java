package org.emmek.bwfinale.controllers.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long idNumero) {
        super("Elemento con id " + idNumero + " non trovato");
    }


}