package org.emmek.bwfinale.controllers;

import org.emmek.bwfinale.entities.Comune;
import org.emmek.bwfinale.entities.Provincia;
import org.emmek.bwfinale.exceptions.ResponseMessage;
import org.emmek.bwfinale.helpers.CVSHelperProvincia;
import org.emmek.bwfinale.services.CSVComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/comuni")
public class CSVComuneControllers {
    @Autowired
    CSVComuneService service;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CVSHelperProvincia.hasCSVFormat(file)) {
            try {
                service.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not Upload the File: "+ file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "please upload a csv file.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("")
    public ResponseEntity<List<Comune>> getComuni() {
        try {
            List<Comune> comuneList = service.getAllComune();
            if (comuneList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comuneList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
