package org.emmek.bwfinale.services;

import org.emmek.bwfinale.Enum.StatoFattura;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.payload.FatturaPostDTO;
import org.emmek.bwfinale.payload.FatturaPutDTO;
import org.emmek.bwfinale.repositories.ClienteRepository;
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
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    public Fattura save(FatturaPostDTO body) {
        Cliente cliente = clienteService.findById(body.clienteId());
        Fattura newFattura = new Fattura();
        newFattura.setNumeroFattura(body.numeroFattura());
        newFattura.setImporto(body.importo());
        newFattura.setData(LocalDate.now().minusYears(1));
        newFattura.setCliente(cliente);
        newFattura.setAnno(LocalDate.now().getYear() - 1);
        newFattura.setStato(StatoFattura.DA_APPROVARE);
        return fatturaRepository.save(newFattura);
    }


    public Fattura findById(long id) {

        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Fattura findAndUpdateById(long idNumero, FatturaPutDTO body) {
        Fattura fattura = this.findById(idNumero);

        fattura.setId(idNumero);
        fattura.setImporto(body.importo());
        fattura.setNumeroFattura(body.numeroFattura());
        fattura.setStato(StatoFattura.valueOf(body.stato()));

        return fattura;

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
