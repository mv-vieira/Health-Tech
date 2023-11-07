package com.br.healthtech.controllers;

import com.br.healthtech.models.AmbulanciaModel;
import com.br.healthtech.models.PacienteModel;
import com.br.healthtech.services.AmbulanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/health-tech/ambulancias")
public class AmbulanciaController {

    @Autowired
    private AmbulanciaService ambulanciaService;

    @GetMapping
    public ResponseEntity<Page<AmbulanciaModel>> getAllPacientes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ambulanciaService.findAll(pageable));
    }
}
