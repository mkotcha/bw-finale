package org.emmek.bwfinale.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.emmek.bwfinale.config.EmailSender;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.entities.Fattura;
import org.emmek.bwfinale.exceptions.BadRequestException;
import org.emmek.bwfinale.payload.ClientePostDTO;
import org.emmek.bwfinale.payload.EmailDTO;
import org.emmek.bwfinale.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/clienti")
@Tag(name = "clienti", description = "API gestione clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailSender emailSender;

    @Operation(summary = "lista di tutti i clienti con vari filtri", description = "combina più query per affinare la ricerca")
    @GetMapping("")
//    @Tag(name = "find clienti", description = "query ricerca clienti")
    public Page<Cliente> getClienti(@RequestParam(defaultValue = "0")
                                    @Parameter(description = "ritorna i clienti con un fatturato maggiore del valore inserito, da usare con fatturatoLess per ottenere un valore compreso")
                                    double fatturatoGreater,
                                    @RequestParam(defaultValue = "0")
                                    @Parameter(description = "ritorna i clienti con un fatturato minore del valore inserito, da usare con fatturatoGreater per ottenere un valore compreso\"")
                                    double fatturatoLess,
                                    @RequestParam(defaultValue = "")
                                    @Parameter(description = "ritorna tutte le fatture emesse nel giorno inserito nel formato yyyy-mm-gg")
                                    String dataInserimento,
                                    @RequestParam(defaultValue = "")
                                    @Parameter(description = "ritorna tutte le fatture modificate/aggiornate nel giorno inserito nel formato yyyy-mm-gg")
                                    String dataUltimoContatto,
                                    @RequestParam(defaultValue = "")
                                    @Parameter(description = "ritorna un elenco di clienti contenenti la stringa inserita nella ragione sociale")
                                    String ragioneSociale,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sort) {
        return clienteService.getClienti(fatturatoGreater, fatturatoLess, dataInserimento, dataUltimoContatto, ragioneSociale, page, size, sort);
    }

    @Operation(summary = "crea un nuovo cliente")
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente postCliente(@RequestBody @Validated ClientePostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return clienteService.save(body);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateClienteById(@PathVariable @Parameter(description = "id del cliente da modificare") long id, @RequestBody @Validated ClientePostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return clienteService.updateById(id, body);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEventById(@PathVariable long id) {
        clienteService.deleteById(id);
    }

    @PostMapping("/{id}/logo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadExample(@PathVariable long id, @RequestParam("logo") MultipartFile body) throws IOException {
        return clienteService.uploadPicture(id, body);
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable long id) {
        return clienteService.findById(id);
    }

    @GetMapping("/{clienteId}/fatture")
    public Page<Fattura> findFattureByClienteId(@PathVariable long clienteId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sort) {
        return clienteService.findFattureByClienteId(clienteId, page, size, sort);
    }

    @PostMapping("/{id}/sendmail")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void sendMail(@PathVariable long id, @RequestBody @Validated EmailDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            Cliente cliente = clienteService.findById(id);
            emailSender.sendEmail(cliente, body);
        }
    }
}

