package org.emmek.bwfinale.controllers;

import org.emmek.bwfinale.entities.Utente;
import org.emmek.bwfinale.exceptions.BadRequestException;
import org.emmek.bwfinale.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UtenteController{
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy) {
        return utenteService.getUsers(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Utente findById(@PathVariable long id){return utenteService.findById(id);}

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Utente findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated Utente body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return utenteService.findByIdAndUpdate(id, body);
        }
        }

        @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        utenteService.findByIdAndDelete(id);
        }
    }

