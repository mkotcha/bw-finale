package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
@Tag(name = "Province", description = "API elenco province senza la i")
public class ProvinciaControllers {
    @Autowired
    ProvinciaService service;


    @GetMapping("")
    public ResponseEntity<List<Provincia>> getProvincie() {
        try {
            List<Provincia> provinciaList = service.findAll();
            if (provinciaList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(provinciaList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
