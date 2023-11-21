package org.emmek.bwfinale.controller;

import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.exceptions.BadRequestException;
import org.emmek.bwfinale.payloads.ClientePostDTO;
import org.emmek.bwfinale.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") double fatturatoGreater,
                                    @RequestParam(defaultValue = "0") double fatturatoLess,
                                    @RequestParam(defaultValue = "") String dataInserimento,
                                    @RequestParam(defaultValue = "") String dataUltimoContatto,
                                    @RequestParam(defaultValue = "") String ragioneSociale,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sort) {
        return clienteService.getClienti(fatturatoGreater, fatturatoLess, dataInserimento, dataUltimoContatto, ragioneSociale, page, size, sort);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente postCliente(@RequestBody @Validated ClientePostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return clienteService.save(body);
        }
    }
}
