package org.emmek.bwfinale.services;

import org.emmek.bwfinale.controllers.exceptions.NotFoundException;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.payloads.FatturaDTO;
import org.emmek.bwfinale.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FatturaService {
    @Autowired
    FatturaRepository fatturaRepository;

    public Fattura save(FatturaDTO body) {
        Fattura newFattura = new Fattura();

        newFattura.setImporto(body.importo());
        newFattura.setData(body.data());

        return fatturaRepository.save(newFattura);
    }

    public Page<Fattura> getFattura(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return fatturaRepository.findAll(pageable);
    }

    public Fattura findByIdNumero(long idNumero) {

        return fatturaRepository.findById(idNumero).orElseThrow(() -> new NotFoundException(idNumero));

    }
}
