package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Indirizzo;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {

    @Autowired
    IndirizzoRepository repository;

    public Indirizzo save(Indirizzo indirizzo) {
        return repository.save(indirizzo);
    }

    public Page<Indirizzo> getIndirizzi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return repository.findAll(pageable);
    }

    public Indirizzo findById(long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findAndDelete(long id) throws NotFoundException {
        Indirizzo found = this.findById(id);

        repository.delete(found);
    }


}
