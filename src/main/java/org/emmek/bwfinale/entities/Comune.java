package org.emmek.bwfinale.entities;

import io.swagger.v3.oas.annotations.Hidden;
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
@Hidden
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
