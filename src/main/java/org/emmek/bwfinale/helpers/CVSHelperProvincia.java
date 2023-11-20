package org.emmek.bwfinale.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.emmek.bwfinale.entities.Provincia;
import org.springframework.context.annotation.ComponentScanBeanDefinitionParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CVSHelperProvincia {
    public static String TYPE = "text/csv";
    static String[] Headers= {"Id","Sigla","Provincia","Regione"};
    public static boolean hasCSVFormat(MultipartFile multipartFile){
        return TYPE.equals(multipartFile.getContentType());
    }
    public static List<Provincia> csvToProvincia(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Provincia> provinciaList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord: csvRecords) {
                Provincia provincia =new Provincia(
                        csvRecord.get("Sigla"),
                        csvRecord.get("Provincia"),
                        csvRecord.get("Regione")
                );
                provinciaList.add(provincia);

            }
            return provinciaList;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV File: " + e.getMessage());
        }

    }

}
