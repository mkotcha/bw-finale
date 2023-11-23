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

    private static Provincia getProvincia(CSVRecord record) {
        Provincia provincia = new Provincia();
        if (record.get(1).equals("Verbania")) {
            provincia.setSigla(record.get(0));
            provincia.setProvincia("Verbano-Cusio-Ossola");
        } else if (record.get(1).equals("Carbonia Iglesias")) {
            provincia.setSigla("SU");
            provincia.setProvincia("Sud-Sardegna");
        } else {
            provincia.setSigla(record.get(0));
            provincia.setProvincia(record.get(1));
        }
        provincia.setRegione(record.get(2));
        return provincia;
    }

    public void loadProvincieCsv(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').parse(reader);
            boolean flag = false;
            for (CSVRecord record : records) {
                if (flag) {
                    if (!(record.get(2).equals("Medio Campidano") || record.get(2).equals("Ogliastra") || record.get(2).equals("Olbia-Tempio"))) {
                        Provincia provincia = getProvincia(record);
                        provinciaRepository.save(provincia);
                    }
                } else {
                    flag = true;
                }
            }
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


