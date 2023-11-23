package org.emmek.bwfinale.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record StatoFatturaDTO(
        @NotEmpty(message = "Lo stato è un campo obbligatorio!")
        @Size(min = 3, max = 12, message = "Il nome deve essere compreso tra 3 e 12 caratteri")
        String stato
) {
}
