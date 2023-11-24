package com.br.healthtech.web.controller;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.domain.services.OcorrenciaService;
import com.br.healthtech.web.dto.AmbulanciaDto;
import com.br.healthtech.web.dto.OcorrenciaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/health-tech/ocorrencia")
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;

    // Listar todas as ocorrências por páginação
    @GetMapping("/listar-ocorrencias")
    public ResponseEntity<Page<Ocorrencia>> getAllOcorrencias(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                              Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ocorrenciaService.findAll(pageable));
    }

    // Achando Ocorrência pelo Número de Protocolo
    @GetMapping("/buscar-ocorrencia")
    public ResponseEntity<Optional<Ocorrencia>> getByProtocolo(@RequestParam String protocolo) throws Exception {
        Optional<Ocorrencia> ocorrencia = ocorrenciaService.findByProtocolo(protocolo);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
    }

    // Alterar uma ocorrência pelo protocolo.
    @PutMapping("/atualizar-ocorrencia")
    public ResponseEntity<Object> updateOcorrencia(@RequestParam String protocolo,
                                                   @RequestBody @Valid OcorrenciaDto ocorrenciaDto) throws Exception {
        try {
            Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaService.findByProtocolo(protocolo);

            if (ocorrenciaOptional.isPresent()) {
                Ocorrencia ocorrencia = ocorrenciaOptional.get();
                if (ocorrencia.getDataHora() != null) {
                    ocorrencia.setDescricao(ocorrenciaDto.descricao());
                    ocorrencia.setEndereco(ocorrenciaDto.endereco());
                } else {
                    ocorrencia.setDataHora(LocalDateTime.now());
                    ocorrencia.setDescricao(ocorrenciaDto.descricao());
                    ocorrencia.setEndereco(ocorrenciaDto.endereco());
                }
                Integer idAmbulancia = ocorrencia.getAmbulancia().getId();
                Integer idPaciente = ocorrencia.getPaciente().getId();
                Integer idHospital = ocorrencia.getHospital().getId();
                ocorrenciaService.saveOcorrencia(ocorrencia, idAmbulancia, idPaciente, idHospital);
                return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada com o protocolo: " + protocolo);
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível alterar dados da ocorrência: " + e.getMessage());
        }

    }

    // Criar nova ocorrência
    @PostMapping("/cadastrar-ocorrencia")
    public ResponseEntity<Object> saveOcorrencia(@RequestBody @Valid OcorrenciaDto ocorrenciaDto,
                                                 @RequestParam Integer idAmbulancia,
                                                 @RequestParam Integer idPaciente,
                                                 @RequestParam Integer idHospital) throws Exception {

        var ocorrencia = new Ocorrencia();
        BeanUtils.copyProperties(ocorrenciaDto, ocorrencia);
//        ocorrencia.setDataHora(LocalDateTime.now());
        ocorrenciaService.saveOcorrencia(ocorrencia, idAmbulancia, idPaciente, idHospital);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ocorrência cadastrada com sucesso!");
    }

}
