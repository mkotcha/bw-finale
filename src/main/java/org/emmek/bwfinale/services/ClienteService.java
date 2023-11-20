package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.payloads.ClientePostDTO;
import org.emmek.bwfinale.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Cliente> clienti = clienteRepository.findAll();

        if (fatturatoGreater != 0) {
            clienti = clienti.stream()
                    .filter(c -> c.getFatturatoAnnuale() > fatturatoGreater)
                    .collect(Collectors.toList());
        }

        if (fatturatoLess != 0) {
            clienti = clienti.stream()
                    .filter(c -> c.getFatturatoAnnuale() < fatturatoLess)
                    .collect(Collectors.toList());
        }

        if (!dataInserimento.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getDataInserimento().equals(LocalDate.parse(dataInserimento)))
                    .collect(Collectors.toList());
        }

        if (!dataUltimoContatto.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getDataUltimoContatto().equals(LocalDate.parse(dataUltimoContatto)))
                    .collect(Collectors.toList());
        }

        if (!ragioneSociale.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getRagioneSociale().contains(ragioneSociale))
                    .collect(Collectors.toList());
        }
        
        return new PageImpl<>(clienti, pageable, clienti.size());

    }
}
