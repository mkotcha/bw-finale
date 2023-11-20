package org.emmek.bwfinale.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.services.CSVProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CVSHelperComune {

    @Autowired
    static CSVProvinciaService service;

    public static String TYPE = "text/csv";
    static String[] Headers= {"Codice Provincia (Storico)(1)","Progressivo del Comune (2)","Denominazione in italiano","Provincia"};
    public static boolean hasCSVFormat(MultipartFile multipartFile){
        return TYPE.equals(multipartFile.getContentType());
    }
    public static List<Comune> csvToProvincia(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Comune> comuneList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord: csvRecords) {
                Comune comune =new Comune(
                      Integer.parseInt(csvRecord.get("Codice Provincia (Storico)(1)")),
                    Long.parseLong(csvRecord.get("Progressivo del Comune (2)")),
                        csvRecord.get("Denominazione in italiano"),
                     service.findByName(csvRecord.get(4))
                );
                comuneList.add(comune);

            }
            return comuneList;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV File: " + e.getMessage());
        }

    }
}
