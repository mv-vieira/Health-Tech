package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.domain.services.AmbulanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/health-tech/ambulancias")
public class AmbulanciaController {

    @Autowired
    private AmbulanciaService ambulanciaService;

    // Listar todas as ambulancias
    @GetMapping
    public ResponseEntity<Page<Ambulancia>> getAllAmbulancias(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ambulanciaService.findAll(pageable));
    }

    // Listar ambulancia pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ambulancia>> getAmbulanciaById(@PathVariable int id) {
        Optional<Ambulancia> ambulancia = ambulanciaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ambulancia);
    }
}
