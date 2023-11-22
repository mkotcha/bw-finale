package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comuni")
@Tag(name = "Comuni", description = "API elenco comuni")
public class ComuneControllers {
    @Autowired
    ComuneService service;


    @GetMapping("")
    public ResponseEntity<List<Comune>> getComuni() {
        try {
            List<Comune> comuneList = service.getAllComune();
            if (comuneList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comuneList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
