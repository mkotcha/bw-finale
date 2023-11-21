package org.emmek.bwfinale.repositories;

import org.emmek.bwfinale.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune,Long> {
}
