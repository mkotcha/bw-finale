package org.emmek.bwfinale.repositories;

import org.emmek.bwfinale.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

    public Optional<StatoFattura> findByStato(String stato);

    public Optional<StatoFattura> findById(long id);
}
