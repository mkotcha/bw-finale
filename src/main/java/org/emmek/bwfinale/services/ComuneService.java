package org.emmek.bwfinale.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.repositories.ProvinciaRepository;
import org.emmek.bwfinale.repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;

@Service
@Slf4j
public class ComuneService {
    @Autowired
    ComuneRepository comuneRepository;

    @Autowired
    ProvinciaService provinciaService;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void loadComuniCsv(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').parse(reader);
            boolean flag = false;
            for (CSVRecord record : records) {
                if (flag) {
                    Provincia newProvincia = new Provincia("VCS", "Verbano-Cusio-Ossola", "Piemonte");
                    newProvincia = new Provincia("VCS", "Sud-Sardegna", "Sardegna");
                    String provinciaStr = record.get(3);

                    switch (provinciaStr) {
//                        case "Verbano-Cusio-Ossola" -> {
//
//                            provinciaRepository.save(newProvincia);
//                        }
                        case "Valle d'Aosta/Vallée d'Aoste" -> provinciaStr = "Aosta";
                        case "La Spezia" -> provinciaStr = "La-Spezia";
                        case "Reggio nell'Emilia" -> provinciaStr = "Reggio-Emilia";
                        case "Reggio Calabria" -> provinciaStr = "Reggio-Calabria";
//                        case "Sud Sardegna" ->
////                        Provincia newProvincia = new Provincia("VCS", "Sud-Sardegna", "Sardegna");
////                        provinciaRepository.save(newProvincia);
//                                provinciaStr = "Sud-Sardegna";
                        default -> {
                            String[] strPart = provinciaStr.split("[ ;/-]");
                            if (strPart[0].length() > 3) provinciaStr = strPart[0];
                            provinciaStr = Normalizer.normalize(provinciaStr, Normalizer.Form.NFD);
                            provinciaStr = provinciaStr.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                            log.info("****************** " + provinciaStr);
                        }
                    }

                    Provincia provincia = provinciaService.findByProvinciaContaining(provinciaStr);

                    Comune comune = new Comune();
                    comune.setNomeComune(record.get(2));
                    comune.setProvincia(provincia);
                    comuneRepository.save(comune);
                } else {
                    flag = true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comune> getAllComune() {
        return comuneRepository.findAll();
    }

}