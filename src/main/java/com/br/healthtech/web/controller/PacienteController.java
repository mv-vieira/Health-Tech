package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Paciente;
import com.br.healthtech.domain.services.PacienteService;
import com.br.healthtech.web.dto.PacienteDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/health-tech")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    // Cadastrar novo Paciente
    @PostMapping("/paciente")
    public ResponseEntity<Object> savePaciente(@RequestBody @Valid PacienteDto pacienteDto) throws Exception {

        if (pacienteService.existsByCpf(pacienteDto.cpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um paciente com esse CPF");
        }

        var pacienteModel = new Paciente();
        BeanUtils.copyProperties(pacienteDto, pacienteModel);
        pacienteService.savePaciente(pacienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso!");
    }

    // Listar todos os pacientes por páginas
    @GetMapping("/paciente")
    public ResponseEntity<Page<Paciente>> getAllPacientes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                          Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll(pageable));
    }

    // Listar paciente pelo id
    @GetMapping("/paciente/{id}")
    public ResponseEntity<Object> getByIdPaciente(@PathVariable(value = "id") Integer id) throws Exception {

        Optional<Paciente> pacienteModelOptional = pacienteService.findById(id);

        return pacienteModelOptional
                .<ResponseEntity<Object>>map(paciente -> ResponseEntity.status(HttpStatus.OK).body(paciente))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado."));
    }

    // Listar paciente pelo CPF
    @GetMapping("/paciente/")
    public ResponseEntity<Object> findByCpf(@RequestParam String cpf) throws Exception {

        Optional<Paciente> pacienteModelOptional = pacienteService.findByCpf(cpf);

        if (pacienteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente não foi encontrado com os dados fornecidos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacienteModelOptional);

    }

    // Atualizar dados do paciente
    @PutMapping("/paciente/{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid PacienteDto pacienteDto)  {

        Optional<Paciente> pacienteModelOptional = pacienteService.findById(id);

        if (pacienteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        var pacienteModel = new Paciente();
        BeanUtils.copyProperties(pacienteDto, pacienteModel);
        pacienteModel.setId(pacienteModelOptional.get().getId());
        pacienteService.savePaciente(pacienteModel);
        return ResponseEntity.status(HttpStatus.OK).body("Dados do Paciente atualizados com sucesso!");

    }

}
