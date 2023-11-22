package org.emmek.bwfinale.repositories;

import org.emmek.bwfinale.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Optional<Cliente> findById(long id);

    public Page<Cliente> findByFatturatoAnnualeGreaterThan(double fatturatoAnnuale, Pageable pageable);

    public Page<Cliente> findByFatturatoAnnualeLessThan(double fatturatoAnnuale, Pageable pageable);

    public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    public Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, Pageable pageable);

}
