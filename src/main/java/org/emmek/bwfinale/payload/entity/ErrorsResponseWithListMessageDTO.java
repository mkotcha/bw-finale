package org.emmek.bwfinale.payload.entity;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.Date;
import java.util.List;

@Hidden
public record ErrorsResponseWithListMessageDTO(String message, Date timeStamp, List<String> errorList) {
}
