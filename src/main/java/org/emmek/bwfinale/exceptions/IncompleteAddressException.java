package org.emmek.bwfinale.exceptions;

import lombok.Getter;

@Getter
public class IncompleteAddressException extends RuntimeException {
    public IncompleteAddressException(String message) {
        super(message);
    }
}
