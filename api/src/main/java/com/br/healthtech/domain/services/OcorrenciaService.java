package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.domain.entity.Hospital;
import com.br.healthtech.domain.entity.Ocorrencia;
import com.br.healthtech.domain.entity.Paciente;
import com.br.healthtech.domain.services.utils.ProtocoloGenerator;
import com.br.healthtech.infra.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;
    @Autowired
    private AmbulanciaService ambulanciaService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private HospitalService hospitalService;
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

    // Find all
    public Page<Ocorrencia> findAll(Pageable page){
        return ocorrenciaRepository.findAll(page);
    }

    // Criar nova ocorrência
    public void saveOcorrencia(Ocorrencia ocorrencia, Integer idAmbulancia, Integer idPaciente, Integer idHospital) throws Exception {
        try{
            Optional<Ambulancia> ambulanciaOptional = ambulanciaService.findById(idAmbulancia);
            Optional<Paciente> pacienteOptional = pacienteService.findById(idPaciente);
            Optional<Hospital> hospitalOptional = hospitalService.findById(idHospital);

            Ambulancia ambulancia = ambulanciaOptional.get();
            Paciente paciente = pacienteOptional.get();
            Hospital hospital = hospitalOptional.get();

            String protocolo = protocoloGenerator.gerarProtocolo();

            ocorrencia.setAmbulancia(ambulancia);
            ocorrencia.setPaciente(paciente);
            ocorrencia.setHospital(hospital);
            ocorrencia.setProtocolo(protocolo);

            ocorrenciaRepository.save(ocorrencia);

        } catch(Exception e){
            throw new Exception("Não foi possível registrar ocorrência" + e.getMessage());
        }

    }
}
