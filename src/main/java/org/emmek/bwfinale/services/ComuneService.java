package org.emmek.bwfinale.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.repositories.ComuneRepository;
import org.emmek.bwfinale.repositories.ProvinciaRepository;
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
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setDelimiter(';')
                    .build()
                    .parse(reader);

            boolean flag = false;
            for (CSVRecord record : records) {
                if (flag) {
                    String provinciaStr = record.get(3);
                    switch (provinciaStr) {
                        case "Valle d'Aosta/Vallée d'Aoste" -> provinciaStr = "Aosta";
                        case "La Spezia" -> provinciaStr = "La-Spezia";
                        case "Reggio nell'Emilia" -> provinciaStr = "Reggio-Emilia";
                        case "Reggio Calabria" -> provinciaStr = "Reggio-Calabria";
                        case "Sud Sardegna" -> provinciaStr = "Sud-Sardegna";
                        default -> {
                            String[] strPart = provinciaStr.split("[ ;/-]");
                            if (strPart[0].length() > 3)
                                provinciaStr = strPart[0];
                            provinciaStr = Normalizer.normalize(provinciaStr, Normalizer.Form.NFD);
                            provinciaStr = provinciaStr.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                            log.info("****** ******* " + provinciaStr);
                        }
                    }

                    Provincia provincia = provinciaService.findByProvinciaContaining(provinciaStr);

                    Comune comune = new Comune();
                    comune.setNome(record.get(2));
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

    public Comune findByNomeAndProvinciaSigla(String nome, String provincia) {
        return comuneRepository.findByNomeAndProvinciaSigla(nome, provincia).orElseThrow(
                () -> new RuntimeException("Comune " + nome + " " + " provincia di  " + provincia + " non trovato"));
    }

}
