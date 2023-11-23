package org.emmek.bwfinale.repositories;

import org.emmek.bwfinale.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

    public StatoFattura findByStato(String stato);

    public StatoFattura findById(long id);
}
