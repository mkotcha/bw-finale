package org.emmek.bwfinale.payload;

import jakarta.validation.constraints.NotNull;

public record FatturaPutDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                            @NotNull(message = "campo del ClienteID obbligatorio!") long clienteId,
                            @NotNull(message = "Campo del numero fattura obbligatorio!") String numeroFattura,
                            @NotNull(message = "Campo dello stato obbligatorio!") String statoFattura
) {
}
