package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.helpers.CVSHelperComune;
import org.emmek.bwfinale.repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVComuneService {
    @Autowired
    ComuneRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Comune> comuneList = CVSHelperComune.csvToProvincia(file.getInputStream());
            repository.saveAll(comuneList);
        } catch (IOException e) {
            throw new RuntimeException("Fail to Store SCV data: "+ e.getMessage());
        }
    }

    public List<Comune> getAllComune() {
        return repository.findAll();
    }

}
