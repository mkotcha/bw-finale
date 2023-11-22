package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.entities.Indirizzo;
import org.emmek.bwfinale.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indirizzi")
@Tag(name = "Indirizzi", description = "API gestione indirizzi")
public class IndirizzoController {
    @Autowired
    private IndirizzoService service;

    @GetMapping("")
    public Page<Indirizzo> getIndirizzi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return service.getIndirizzi(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo saveIndirizzo(@RequestBody Indirizzo body) {
        return service.save(body);
    }

    @GetMapping("/{id}")
    public Indirizzo findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id) {
        service.findAndDelete(id);
    }
}
