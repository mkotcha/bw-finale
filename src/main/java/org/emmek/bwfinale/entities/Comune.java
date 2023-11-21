package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comuni")
public class Comune { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int codiceProvincia;

private int codiceComune;

    private String nome_comune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    public Comune(int i, long l, String denominazioneInItaliano, Provincia byName) {
    }
}
