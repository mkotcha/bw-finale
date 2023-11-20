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
    private int codiceProvincia;

    @Id
    private long id;

    private String nome_comune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
