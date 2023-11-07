package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Paciente;
import com.br.healthtech.infra.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    // Cadastrar Paciente
    @Transactional
    public void savePaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    // Encontrar todos os Pacientes
    public Page<Paciente> findAll(Pageable page) {
        return pacienteRepository.findAll(page);
    }

    // Encontrar Paciente pelo ID
    @Transactional
    public Optional<Paciente> findById(int id) {
        return pacienteRepository.findById(id);
    }

    // Encontrar Paciente pelo CPF
    public boolean existsByCpf(String cpf) {
        return pacienteRepository.existsByCpf(cpf);
    }

    @Transactional
    // Encontrar pelo CPF
    public Optional<Paciente> findByCpf (String cpf)  {
            return pacienteRepository.findByCpf(cpf);
    }

    // Deletar Paciente
    @Transactional
    public void delete(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

}
