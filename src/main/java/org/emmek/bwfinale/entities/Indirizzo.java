package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String via;
    private int civico;
    private String locatital;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;


}
