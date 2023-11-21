package org.emmek.bwfinale.controller;

import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
