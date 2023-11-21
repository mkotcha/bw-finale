package org.emmek.bwfinale.services;

import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.payloads.FatturaDTO;
import org.emmek.bwfinale.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
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
    public Fattura findAndUpdateByIdNumero(long idNumero, Fattura body) {
        Fattura foundF = this.findByIdNumero(idNumero);

        foundF.setId_numero(idNumero);
        foundF.setImporto(body.getImporto());
        foundF.setData(body.getData());

        return foundF;

    }
    public void findAndDeleteByIdNumero(long id) {
        Fattura foundF = this.findByIdNumero(id);
        fatturaRepository.delete(foundF);
    }
}
