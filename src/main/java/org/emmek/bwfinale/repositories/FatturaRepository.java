package org.emmek.bwfinale.repositories;

import org.emmek.bwfinale.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}
