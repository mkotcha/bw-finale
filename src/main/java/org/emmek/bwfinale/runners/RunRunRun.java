package org.emmek.bwfinale.runners;

import org.emmek.bwfinale.services.ComuneService;
import org.emmek.bwfinale.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunRunRun implements CommandLineRunner {

    @Autowired
    ProvinciaService provinciaService;

    @Autowired
    ComuneService comuneService;

    @Override
    public void run(String... args) throws Exception {
//        provinciaService.loadProvincieCsv("file/province-italiane.csv");
//        comuneService.loadComuniCsv("file/comuni-italiani.csv");
    }
}
