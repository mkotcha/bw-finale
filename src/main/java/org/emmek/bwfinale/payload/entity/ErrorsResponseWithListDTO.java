package org.emmek.bwfinale.payload.entity;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(
        Date timestamp,
        List<String> errorsList) {
}
