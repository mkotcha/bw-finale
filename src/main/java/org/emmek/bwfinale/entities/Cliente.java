package org.emmek.bwfinale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.emmek.bwfinale.Enum.TipoCliente;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    List<Fattura> fatture;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ragione_sociale")
    private String ragioneSociale;
    @Column(name = "partita_iva")
    private String partitaIva;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;
    @Column(name = "fatturato_annuale")
    private double fatturatoAnnuale;
    @Column(name = "pec")
    private String pec;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email_contatto")
    private String emailContatto;
    @Column(name = "nome_contatto")
    private String nomeContatto;
    @Column(name = "cognome_contatto")
    private String cognomeContatto;
    @Column(name = "telefono_contatto")
    private String telefonoContatto;
    @Column(name = "logo_aziendale")
    private String logoAziendale;
    @Column(name = "tipo_cliente")
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToOne
    @JoinColumn(name = "indirizzo_1_id")
    private Indirizzo indirizzo1;
    @OneToOne
    @JoinColumn(name = "indirizzo_2_id")
    private Indirizzo indirizzo2;


}
