package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.helpers.CVSHelperProvincia;
import org.emmek.bwfinale.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CSVProvinciaService {
    @Autowired
    ProvinciaRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Provincia> provinciaList = CVSHelperProvincia.csvToProvincia(file.getInputStream());
            repository.saveAll(provinciaList);
        } catch (IOException e) {
            throw new RuntimeException("Fail to Store SCV data: "+ e.getMessage());
        }
    }

    public List<Provincia> getAllProvincia() {
        return repository.findAll();
    }

public Provincia findByName(String name) {
       return repository.findByProvincia(name).orElseThrow(()-> new NotFoundException("Provincia con nome: "+name+" non Trovato!"));
}
}
