package org.emmek.bwfinale.payload.entity;

import java.util.Date;

public record ErrorsResponseDTO(String message, Date timestamp) {
    public static record ErrorsResponseDTO(String message, Date timestamp) {
    }
}
