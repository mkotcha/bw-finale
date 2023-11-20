package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.payloads.ClientePostDTO;
import org.emmek.bwfinale.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(ClientePostDTO body) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setDataInserimento(LocalDate.parse(body.dataInserimento()));
        return clienteRepository.save(cliente);
    }

    public Cliente findById(long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
    }

    public Page<Cliente> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> findByFatturatoAnnualeGreaterThan(double fatturatoAnnuale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByFatturatoAnnualeGreaterThan(fatturatoAnnuale, pageable);
    }

    public Page<Cliente> findByFatturatoAnnualeLessThan(double fatturatoAnnuale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByFatturatoAnnualeLessThan(fatturatoAnnuale, pageable);
    }

    public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByDataInserimento(dataInserimento, pageable);
    }

    public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByDataUltimoContatto(dataUltimoContatto, pageable);
    }

    public Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByRagioneSocialeContaining(ragioneSociale, pageable);
    }

    public Page<Cliente> getClienti(double fatturatoGreater, double fatturatoLess, String dataInserimento, String dataUltimoContatto, String ragioneSociale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return null;
    }
}
