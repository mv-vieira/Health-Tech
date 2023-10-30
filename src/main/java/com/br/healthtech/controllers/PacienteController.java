package com.br.healthtech.controllers;

import com.br.healthtech.dtos.PacienteDto;
import com.br.healthtech.models.PacienteModel;
import com.br.healthtech.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/health-tech")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    // Cadastrar novo Paciente
    @PostMapping
    public ResponseEntity<Object> savePaciente ( @RequestBody @Valid PacienteDto pacienteDto) {
        var pacienteModel = new PacienteModel();
        BeanUtils.copyProperties(pacienteDto, pacienteModel);
        pacienteService.savePaciente(pacienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<Page<PacienteModel>> getAllPacientes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                               Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll(pageable));
    }
}
