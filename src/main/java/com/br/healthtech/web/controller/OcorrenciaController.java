package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.domain.services.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-tech/ocorrencia")
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;

    @GetMapping
    public ResponseEntity<Ocorrencia> getByProtocolo(@RequestParam Integer protocolo){
        Ocorrencia ocorrencia = ocorrenciaService.findByProtocolo(protocolo);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
    }
}
