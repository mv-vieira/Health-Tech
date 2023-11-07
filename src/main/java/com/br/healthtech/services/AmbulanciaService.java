package com.br.healthtech.services;

import com.br.healthtech.models.AmbulanciaModel;
import com.br.healthtech.repositories.AmbulanciaRepository;
import com.br.healthtech.repositories.PacienteRepository;
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
    public Page<AmbulanciaModel> findAll(Pageable page) {
        return ambulanciaRepository.findAll(page);
    }

}

