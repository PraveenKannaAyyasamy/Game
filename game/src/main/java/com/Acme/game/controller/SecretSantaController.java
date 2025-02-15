package com.Acme.game.controller;

import com.Acme.game.service.impl.CSVService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/santa")
public class SecretSantaController {

    private final CSVService csvService;

    public SecretSantaController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/assignments/generate")
    public ResponseEntity<byte[]> generateAssignments(@RequestParam("employeeFile") MultipartFile employeeFile,
                                                      @RequestParam(value = "lastYearFile",required = false) MultipartFile lastYearFile) throws IOException {

        byte[] csvBytes = csvService.generateCSV(employeeFile, lastYearFile );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=new_assignments.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(csvBytes);
    }

}

