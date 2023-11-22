package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.emmek.bwfinale.Enum.StatoFattura;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "numero_fattura")
    private String numeroFattura;
    private double importo;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoFattura stato;
    private int anno;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
