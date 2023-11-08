package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.infra.repository.AmbulanciaRepository;
import com.br.healthtech.infra.repository.PacienteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    // Find by ID
    @Transactional
    public Optional<Ambulancia> findById(int id){
       Optional<Ambulancia> ambulanciaOptional = ambulanciaRepository.findById(id);

       if(ambulanciaOptional.isPresent()){
           Ambulancia ambulancia = ambulanciaOptional.get();
           ambulancia.getPacientes().size();
           return Optional.of(ambulancia);
       } else {
           throw new EntityNotFoundException("Ambulância não encontrada");
       }


    }

}

