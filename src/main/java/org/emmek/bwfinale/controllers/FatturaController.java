package org.emmek.bwfinale.controllers;

import org.emmek.bwfinale.Enum.StatoFattura;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.payloads.FatturaDTO;
import org.emmek.bwfinale.services.ClienteService;
import org.emmek.bwfinale.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody FatturaDTO body) {
        return fatturaService.save(body);
    }

    @GetMapping("")
    public Page<Fattura> getFatturaByFiltro(
            @RequestParam long clienteId,
            @RequestParam StatoFattura statoFattura,
            @RequestParam LocalDate data,
            @RequestParam int anno,
            @RequestParam(defaultValue = "0") double importoMin,
            @RequestParam(defaultValue = "0") double importoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy) {
        return fatturaService.getFatture(importoMax, importoMin, data, anno, clienteId, statoFattura, page, size, orderBy);
    }

    @GetMapping("/{idNumero}")
    public Fattura findByIdNumero(@PathVariable long idNumero) {
        return fatturaService.findByIdNumero(idNumero);
    }

    @PutMapping("/{idNumero}")
    public Fattura findAndUpdateByIdNumero(@PathVariable long idNumero, @RequestBody Fattura body) {
        return fatturaService.findAndUpdateByIdNumero(idNumero, body);
    }

    @DeleteMapping("/{idNumero}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteByIdNumero(@PathVariable long idNumero) {
        fatturaService.findAndDeleteByIdNumero(idNumero);
    }
}