package org.emmek.bwfinale.services;

import org.emmek.bwfinale.Enum.StatoFattura;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.payloads.FatturaDTO;
import org.emmek.bwfinale.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

//    public Page<Fattura> getFattureByFiltro(Cliente cliente, StatoFattura stato, LocalDate date, int anno, double importoMin, double importoMax, int page, int size, String orderBy) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
//        return fatturaRepository.findByClienteAndStatoAndDataAndAnnoAndImporto(cliente, stato, date, anno, importoMin, importoMax, pageable);
//    }

    public Page<Fattura> getFatture(double importoGreater, double importoLess, LocalDate data, int anno, long clienteId, StatoFattura statoFattura, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        List<Fattura> fatture = fatturaRepository.findAll();
        if (importoGreater != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() > importoGreater)
                    .collect(Collectors.toList());
        }

        if (importoLess != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() < importoLess)
                    .collect(Collectors.toList());
        }

        if (data != null) {
            fatture = fatture.stream()
                    .filter(f -> f.getData().equals(data))
                    .collect(Collectors.toList());
        }

        if (anno != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getAnno() == anno)
                    .collect(Collectors.toList());
        }

        if (clienteId != 0) {
            Cliente cliente = new Cliente();
            fatture = fatture.stream()
                    .filter(f -> f.getCliente().equals(cliente))
                    .collect(Collectors.toList());
        }

        if (statoFattura != null) {
            fatture = fatture.stream()
                    .filter(f -> f.getStato().equals(statoFattura))
                    .collect(Collectors.toList());
        }

        return new PageImpl<>(fatture, pageable, fatture.size());
    }


    public void findAndDeleteByIdNumero(long id) {
        Fattura foundF = this.findByIdNumero(id);
        fatturaRepository.delete(foundF);
    }
}
