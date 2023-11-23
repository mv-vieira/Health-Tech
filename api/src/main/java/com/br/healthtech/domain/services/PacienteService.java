package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.domain.entity.Paciente;
import com.br.healthtech.infra.repository.AmbulanciaRepository;
import com.br.healthtech.infra.repository.PacienteRepository;
import com.br.healthtech.web.dto.dtoPacienteAmbulancia.PacienteAmbulanciaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    AmbulanciaService ambulanciaService;

    // Cadastrar Paciente
    @Transactional
    public void savePaciente(Paciente paciente, Integer idAmbulancia)  {
        try {
            if (!pacienteRepository.existsByCpf(paciente.getCpf())) {
                Optional<Ambulancia> ambulanciaOptional = ambulanciaService.findById(idAmbulancia);
                if (ambulanciaOptional.isPresent()) {
                    Ambulancia ambulancia = ambulanciaOptional.get();
                    paciente.setAmbulancia(ambulancia);
                    pacienteRepository.save(paciente);
                } else {
                    throw new RuntimeException("Ambulância não encontrada.");
                }
            }

        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException("Paciente não pode ser cadastrado.");
        }
    }

    // Encontrar todos os Pacientes
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Page<PacienteAmbulanciaDTO> findAllPacientesPage(Pageable pageable){
        Page<Paciente> pacientesPage = pacienteRepository.findAll(pageable);

        List<PacienteAmbulanciaDTO> pacientesDTOList = pacientesPage.getContent().stream()
                .map(PacienteAmbulanciaDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(pacientesDTOList, pageable, pacientesPage.getTotalElements());
    }

    // Encontrar Paciente pelo ID
    @Transactional
    public Optional<Paciente> findById(int id) {
        return pacienteRepository.findById(id);
    }

    // Existe Paciente com o CPF
    public boolean existsByCpf(String cpf) {
        return pacienteRepository.existsByCpf(cpf);
    }

    @Transactional
    // Encontrar pelo CPF
    public Optional<Paciente> findByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    // Deletar Paciente
    @Transactional
    public void delete(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

}
