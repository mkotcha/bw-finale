package org.emmek.bwfinale.services;

import org.emmek.bwfinale.Enum.StatoFattura;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.payload.entity.FatturaDTO;
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

    public Fattura save(FatturaDTO body, Cliente cliente) {
        Fattura newFattura = new Fattura();

        newFattura.setImporto(body.importo());
        newFattura.setData(LocalDate.now().minusYears(1));
        newFattura.setCliente(cliente);
        newFattura.setAnno(LocalDate.now().getYear() - 1);
        newFattura.setStato(StatoFattura.PAGATA);

        return fatturaRepository.save(newFattura);
    }


    public Fattura findById(long id) {

        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Fattura findAndUpdateByIdNumero(long idNumero, Fattura body) {
        Fattura foundF = this.findById(idNumero);

        foundF.setId_numero(idNumero);
        foundF.setImporto(body.getImporto());
        foundF.setData(body.getData());

        return foundF;

    }


    public Page<Fattura> getFatture(double importoGreater, double importoLess, String data, StatoFattura statoFattura, int anno, long clientId, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        List<Fattura> fatture = fatturaRepository.findAll();
        if (importoGreater != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() < importoGreater)
                    .collect(Collectors.toList());
        }

        if (importoLess != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() > importoLess)
                    .collect(Collectors.toList());
        }

        if (!data.isEmpty()) {
            fatture = fatture.stream()
                    .filter(f -> f.getData().equals(LocalDate.parse(data)))
                    .collect(Collectors.toList());
        }


        if (statoFattura != null) {
            fatture = fatture.stream()
                    .filter(f -> f.getStato().equals(statoFattura))
                    .collect(Collectors.toList());
        }
        if (anno != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getAnno() == anno)
                    .collect(Collectors.toList());
        }
        if (clientId != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getCliente().getId() == clientId)
                    .collect(Collectors.toList());
        }


        return new PageImpl<>(fatture, pageable, fatture.size());
    }


    public void findAndDeleteByIdNumero(long id) {
        Fattura foundF = this.findById(id);
        fatturaRepository.delete(foundF);
    }
}
