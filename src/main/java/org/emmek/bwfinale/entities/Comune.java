package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comuni")
@NoArgsConstructor
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome_comune")
    private String nomeComune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
