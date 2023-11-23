package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.StatoFattura;
import org.emmek.bwfinale.payload.StatoFatturaDTO;
import org.emmek.bwfinale.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoFatturaService {

    @Autowired
    StatoFatturaRepository statoFatturaRepository;

    public StatoFattura save(StatoFatturaDTO body) {
        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setStato(body.stato());
        try {
            return statoFatturaRepository.save(statoFattura);
        } catch (Exception e) {
            throw new RuntimeException("valore dello stato " + body.stato() + " già presente nel database");
        }

    }

    public List<StatoFattura> findAll() {
        return statoFatturaRepository.findAll();
    }

    public StatoFattura findByStato(String stato) {
        return statoFatturaRepository.findByStato(stato).orElseThrow(() -> new RuntimeException("Stato " + stato + " non trovato"));
    }
}
