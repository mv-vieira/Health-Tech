package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.domain.services.utils.ProtocoloGenerator;
import com.br.healthtech.infra.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;
    @Autowired
    private ProtocoloGenerator protocoloGenerator;

    //Find by Numero Protocolo
    @Transactional
    public Optional<Ocorrencia> findByProtocolo(String protocolo) throws Exception{
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findByProtocolo(protocolo);
        if(ocorrencia == null){
            throw new Exception("Ocorrência com protocolo: " + protocolo + " não foi encontrada");
        }
        return ocorrencia;
    }

    // Find by ID
    @Transactional
    public Optional<Ocorrencia> findById(int id) throws Exception {
        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaRepository.findById(id);

        if (ocorrenciaOptional.isPresent()) {
            Ocorrencia ocorrencia = ocorrenciaOptional.get();
            return Optional.of(ocorrencia);
        } else {
            throw new EntityNotFoundException("Ocorrência não encontrada");
        }
    }

    // Criar nova ocorrência
    public void saveOcorrencia(Ocorrencia ocorrencia){
        String protocolo = protocoloGenerator.gerarProtocolo();
        ocorrencia.setProtocolo(protocolo);
        ocorrenciaRepository.save(ocorrencia);
    }
}
