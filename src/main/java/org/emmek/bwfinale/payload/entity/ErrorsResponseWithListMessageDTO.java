package org.emmek.bwfinale.payload.entity;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListMessageDTO(String message, Date timeStamp, List<String> errorList) {
}
