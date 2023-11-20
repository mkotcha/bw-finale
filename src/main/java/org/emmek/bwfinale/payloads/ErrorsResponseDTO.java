package org.emmek.bwfinale.payloads;

import java.util.Date;

public record ErrorsResponseDTO(String message, Date timestamp) {
}
