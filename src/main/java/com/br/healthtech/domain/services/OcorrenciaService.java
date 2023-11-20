package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.infra.repository.OcorrenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    //Find by Numero Protocolo
    @Transactional
    public Ocorrencia findByProtocolo(Integer protocolo){
        return ocorrenciaRepository.findByProtocolo(protocolo);
    }

    // Criar novo protocolo
    public void saveOcorrencia(Ocorrencia ocorrencia){
        ocorrenciaRepository.save(ocorrencia);
    }
}
