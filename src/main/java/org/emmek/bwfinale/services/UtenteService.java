package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Utente;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;


    public Page<Utente> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con email" + email + "non trovato"));
    }

    public Utente findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByIdAndUpdate(long id, Utente body) throws NotFoundException {
        Utente found = this.findById(id);
        found.setCognome(body.getCognome());
        found.setNome(body.getNome());
        found.setEmail(body.getEmail());
        found.setUsername(body.getUsername());
        return utenteRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }
}
