package org.emmek.bwfinale.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@Hidden
public class ErrorsPayload {
    private String message;
    private Date timestamp;

}
