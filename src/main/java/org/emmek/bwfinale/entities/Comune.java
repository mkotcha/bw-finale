package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comuni")
public class Comune {
    private int codiceProvincia;

    @Id
    private long id;

    private String nome_comune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
