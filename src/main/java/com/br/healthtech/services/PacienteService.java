package com.br.healthtech.services;

import com.br.healthtech.models.PacienteModel;
import com.br.healthtech.repositories.PacienteRepository;
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
    public void savePaciente(PacienteModel pacienteModel) {
        pacienteRepository.save(pacienteModel);
    }

    // Encontrar todos os Pacientes
    public Page<PacienteModel> findAll(Pageable page) {
        return pacienteRepository.findAll(page);
    }

    // Encontrar Paciente pelo ID
    @Transactional
    public Optional<PacienteModel> findById(int id) {
        return pacienteRepository.findById(id);
    }

    // Encontrar Paciente pelo CPF
    public boolean existsByCpf(String cpf) {
        return pacienteRepository.existsByCpf(cpf);
    }

    @Transactional
    // Encontrar pelo CPF
    public Optional<PacienteModel> findByCpf (String cpf)  {
            return pacienteRepository.findByCpf(cpf);
    }

    // Deletar Paciente
    @Transactional
    public void delete(PacienteModel pacienteModel) {
        pacienteRepository.delete(pacienteModel);
    }

}
