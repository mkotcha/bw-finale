package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.entities.StatoFattura;
import org.emmek.bwfinale.payload.StatoFatturaDTO;
import org.emmek.bwfinale.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stati-fattura")
@Tag(name = "Stati fattura", description = "API gestione stati fattura")
public class StatoFatturaController {


    @Autowired
    StatoFatturaService statoFatturaService;

    @GetMapping("")
    public List<StatoFattura> findAll() {
        return statoFatturaService.findAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StatoFattura save(StatoFatturaDTO body) {
        return statoFatturaService.save(body);
    }

}
