package org.emmek.bwfinale.payload.entity;

import jakarta.validation.constraints.NotNull;

public record FatturaDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                         @NotNull(message = "campo del ClienteID obbligatorio!") long clienteId,
                         @NotNull(message = "Campo del numero fattura obbligatorio!") String numeroFattura
) {
}
