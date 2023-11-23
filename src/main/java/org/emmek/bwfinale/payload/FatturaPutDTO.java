package org.emmek.bwfinale.payload;

import jakarta.validation.constraints.NotNull;
import org.emmek.bwfinale.Enum.StatoFattura;
import org.emmek.bwfinale.Enum.ValueOfEnum;

public record FatturaPutDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                            @NotNull(message = "campo del ClienteID obbligatorio!") long clienteId,
                            @NotNull(message = "Campo del numero fattura obbligatorio!") String numeroFattura,
                            @ValueOfEnum(enumClass = StatoFattura.class, message = "Stato fattura deve essere BOZZA, DA_APPROVARE, PAGATA, DA_PAGARE, PAGATA_PARZIALMENTE, IN_RITARDO, ANNULLATA, IN_LAVORAZIONE, IN_SPEDIZIONE, ARCHIVIATA")
                            @NotNull(message = "Campo dello stato obbligatorio!") String stato
) {
}
