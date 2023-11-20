package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.domain.services.OcorrenciaService;
import com.br.healthtech.web.dto.OcorrenciaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/health-tech/ocorrencia")
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;

    // Achando Ocorrência pelo Número de Protocolo
    @Operation(summary = "Buscar recurso pelo nº do protocolo", description = "Obtém uma ocorrência com base no protocolo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocorrência encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ocorrência não encontrada")})
    @GetMapping
    public ResponseEntity<Ocorrencia> getByProtocolo(@RequestParam String protocolo) throws Exception {
        Ocorrencia ocorrencia = ocorrenciaService.findByProtocolo(protocolo);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
    }

    // Alterar uma ocorrência pelo id.
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOcorrencia(@PathVariable Integer id,
                                                   @RequestBody @Valid OcorrenciaDto ocorrenciaDto){

        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaService.findById(id);

        var ocorrencia = new Ocorrencia();
        BeanUtils.copyProperties(ocorrenciaDto,ocorrencia);
        ocorrencia.setId(ocorrenciaOptional.get().getId());
        ocorrencia.setAmbulancia(ocorrenciaOptional.get().getAmbulancia());
        ocorrenciaService.saveOcorrencia(ocorrencia);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
    }
}
