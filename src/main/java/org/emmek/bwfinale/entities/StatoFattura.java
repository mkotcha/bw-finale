package org.emmek.bwfinale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stati_fattura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatoFattura {

    @Column(unique = true)
    String stato;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
