package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.entities.Utente;
import org.emmek.bwfinale.exceptions.BadRequestException;
import org.emmek.bwfinale.payload.NewUtenteDTO;
import org.emmek.bwfinale.payload.UtenteLoginDTO;
import org.emmek.bwfinale.payload.UtenteLoginSuccessDTO;
import org.emmek.bwfinale.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "API gestione utenti")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public UtenteLoginSuccessDTO login(@RequestBody UtenteLoginDTO body) {
        return new UtenteLoginSuccessDTO(authService.autheticateUtente(body));
    }

    @Hidden
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody @Validated NewUtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
