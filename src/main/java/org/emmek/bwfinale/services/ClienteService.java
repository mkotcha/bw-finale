package org.emmek.bwfinale.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.emmek.bwfinale.entities.Cliente;
import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.entities.Indirizzo;
import org.emmek.bwfinale.exceptions.NotFoundException;
import org.emmek.bwfinale.payload.entity.ClientePostDTO;
import org.emmek.bwfinale.repositories.ClienteRepository;
import org.emmek.bwfinale.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    IndirizzoRepository indirizzoRepository;

    @Autowired
    ComuneService comuneService;
    @Autowired
    private Cloudinary cloudinary;

    public Cliente save(ClientePostDTO body) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());

        Comune comune = comuneService.findByNomeAndProvinciaSigla(body.comune1(), body.provincia1());
        Indirizzo indirizzo1 = new Indirizzo(body.via1(), body.civico1(), body.provincia1(), body.cap1(), comune);
        indirizzoRepository.save(indirizzo1);
        cliente.setIndirizzo1(indirizzo1);

        return clienteRepository.save(cliente);
    }

    public Cliente findById(long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
    }


    public Page<Cliente> findByFatturatoAnnualeGreaterThan(double fatturatoAnnuale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByFatturatoAnnualeGreaterThan(fatturatoAnnuale, pageable);
    }

    public Page<Cliente> findByFatturatoAnnualeLessThan(double fatturatoAnnuale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByFatturatoAnnualeLessThan(fatturatoAnnuale, pageable);
    }

    public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByDataInserimento(dataInserimento, pageable);
    }

    public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByDataUltimoContatto(dataUltimoContatto, pageable);
    }

    public Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findByRagioneSocialeContaining(ragioneSociale, pageable);
    }

    public Page<Cliente> getClienti(double fatturatoGreater, double fatturatoLess, String dataInserimento, String dataUltimoContatto, String ragioneSociale, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        List<Cliente> clienti = clienteRepository.findAll();

        if (fatturatoGreater != 0) {
            clienti = clienti.stream()
                    .filter(c -> c.getFatturatoAnnuale() > fatturatoGreater)
                    .collect(Collectors.toList());
        }

        if (fatturatoLess != 0) {
            clienti = clienti.stream()
                    .filter(c -> c.getFatturatoAnnuale() < fatturatoLess)
                    .collect(Collectors.toList());
        }

        if (!dataInserimento.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getDataInserimento().equals(LocalDate.parse(dataInserimento)))
                    .collect(Collectors.toList());
        }

        if (!dataUltimoContatto.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getDataUltimoContatto().equals(LocalDate.parse(dataUltimoContatto)))
                    .collect(Collectors.toList());
        }

        if (!ragioneSociale.isEmpty()) {
            clienti = clienti.stream()
                    .filter(c -> c.getRagioneSociale().contains(ragioneSociale))
                    .collect(Collectors.toList());
        }

        return new PageImpl<>(clienti, pageable, clienti.size());

    }

    public Cliente updateById(long id, ClientePostDTO body) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setDataUltimoContatto(LocalDate.now());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        return clienteRepository.save(cliente);
    }

    public void deleteById(long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        clienteRepository.delete(cliente);
    }

    public String uploadPicture(long id, MultipartFile body) throws IOException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(body.getBytes(), ObjectUtils.emptyMap()).get("url");
        cliente.setLogoAziendale(url);
        clienteRepository.save(cliente);
        return url;
    }
}
