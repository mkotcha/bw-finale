package org.emmek.bwfinale.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.emmek.bwfinale.entities.Indirizzo;

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

        @NotNull(message = "Data inserimento non puo essere null")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data inserimento non valida (yyyy-mm-dd)")
        String dataInserimento,

        @NotNull(message = "Fatturato annuale non puo essere null")
        @NotEmpty(message = "Fatturato annuale non puo essere vuoto")
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

        @NotNull(message = "indirizzo 1 non puo essere null")
        Indirizzo indirizzo1,

        Indirizzo indirizzo2

) {
}
