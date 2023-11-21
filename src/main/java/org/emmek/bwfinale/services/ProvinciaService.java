package org.emmek.bwfinale.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ProvinciaService {
    @Autowired
    ProvinciaRepository provinciaRepository;

    public void loadProvincieCsv(String filePath) {
        log.info("pronto");
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            log.info("letto file");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').parse(reader);
            boolean flag = false;
            for (CSVRecord record : records) {
                if (flag) {
                    Provincia provincia = new Provincia();
                    provincia.setSigla(record.get(0));
                    provincia.setProvincia(record.get(1));
                    provincia.setRegione(record.get(2));
                    provinciaRepository.save(provincia);
                } else {
                    flag = true;
                }
            }
            Provincia newProvincia = new Provincia("VCS", "Verbano-Cusio-Ossola", "Piemonte");
            provinciaRepository.save(newProvincia);
            newProvincia = new Provincia("VCS", "Sud-Sardegna", "Sardegna");
            provinciaRepository.save(newProvincia);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Provincia> findAll() {
        return provinciaRepository.findAll();
    }


    public Provincia findByProvinciaContaining(String s) {
        return provinciaRepository.findByProvinciaContaining(s).orElseThrow(() -> new RuntimeException("Provincia " + s + " non trovata"));
    }
}


