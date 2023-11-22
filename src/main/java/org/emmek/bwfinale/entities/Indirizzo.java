package org.emmek.bwfinale.entities;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "indirizzi")
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private String civico;
    private String provincia;
    private int cap;
    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    public Indirizzo(String via, String civico, String provincia, int cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.provincia = provincia;
        this.cap = cap;
        this.comune = comune;
    }

}
