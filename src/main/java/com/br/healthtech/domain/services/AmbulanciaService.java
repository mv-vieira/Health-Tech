package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.infra.repository.AmbulanciaRepository;
import com.br.healthtech.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AmbulanciaService {

    private final AmbulanciaRepository ambulanciaRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public AmbulanciaService(AmbulanciaRepository ambulanciaRepository, PacienteRepository pacienteRepository) {
        this.ambulanciaRepository = ambulanciaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Find All
    public Page<Ambulancia> findAll(Pageable page) {
        return ambulanciaRepository.findAll(page);
    }

}

