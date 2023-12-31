package org.emmek.bwfinale.services;

import org.emmek.bwfinale.entities.Role;
import org.emmek.bwfinale.entities.Utente;
import org.emmek.bwfinale.exceptions.BadRequestException;
import org.emmek.bwfinale.exceptions.UnauthorizedException;
import org.emmek.bwfinale.payload.NewUtenteDTO;
import org.emmek.bwfinale.payload.UtenteLoginDTO;
import org.emmek.bwfinale.repositories.RoleRepository;
import org.emmek.bwfinale.repositories.UtenteRepository;
import org.emmek.bwfinale.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private RoleRepository roleRepository;

    public String autheticateUtente(UtenteLoginDTO body) {
        Utente utente = utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("credenziali non valide");
        }
    }

    public Utente save(NewUtenteDTO body) throws IOException {
        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + "è già utilizzata!");
        });
        Utente newUtente = new Utente();
        newUtente.setNome(body.nome());
        newUtente.setCognome(body.cognome());
        newUtente.setEmail(body.email());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setUsername(body.username());
        Role userRole = roleRepository.findByRole("ADMIN").orElse(null);
        newUtente.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        return utenteRepository.save(newUtente);
    }
}

