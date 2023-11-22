package org.emmek.bwfinale.payload.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Data Transfer Object per la creazione/modifica di un cliente")
public record ClientePostDTO(

        @NotNull(message = "Ragione sociale non puo essere null")
        @Size(min = 3, max = 30, message = "Ragione sociale deve essere tra 3 e 30 caratteri")
        String ragioneSociale,

        @NotNull(message = "Partita IVA non puo essere null")
        @Size(min = 11, max = 11, message = "Partita IVA deve essere di 11 caratteri")
        String partitaIva,

        @NotNull(message = "email non puo essere null")
        @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email non valida")
        String email,

        @NotNull(message = "Fatturato annuale non puo essere null")
        double fatturatoAnnuale,

        @NotNull(message = "pec non puo essere null")
        @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "pec non valida")
        String pec,

        @NotNull(message = "telefono non puo essere null")
        @NotEmpty(message = "telefono non puo essere vuoto")
        String telefono,

        @NotNull(message = "email contatto non puo essere null")
        @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email contatto non valida")
        String emailContatto,

        @NotEmpty(message = "nome contatto non puo essere vuoto")
        String nomeContatto,

        @NotEmpty(message = "cognome contatto non puo essere vuoto")
        String cognomeContatto,

        @NotEmpty(message = "telefono contatto non puo essere vuoto")
        String telefonoContatto,

        @NotNull(message = "La via pricipale non puo essere nnull")
        String via1,
        @NotNull(message = "il civico pricipale non puo essere null")
        String civico1,

        @NotNull(message = "la provincia principale non puo essere null")
        @Size(min = 2, max = 3, message = "Provincia deve essere tra 2 e 3 caratteri")
        String provincia1,

        @NotNull()
        String comune1,

        int cap1,

        String via2,

        String civico2,

        @Size(min = 2, max = 3, message = "Provincia deve essere tra 2 e 3 caratteri")
        String provincia2,

        String comune2,

        int cap2
) {
}
