package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Hospital;
import com.br.healthtech.domain.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/health-tech/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    // Listar todos os hospitais por páginação
    @GetMapping("/listar-hospitais")
    public ResponseEntity<Page<Hospital>> getAllHospitais(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                          Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findAll(pageable));
    }

    // Encontrar Hospital pelo Municipio
    @GetMapping("/hospitais-municipio")
    public ResponseEntity<List<Hospital>> getByMunicipio(@RequestParam String municipio) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findByMunicipio(municipio));
    }
}
