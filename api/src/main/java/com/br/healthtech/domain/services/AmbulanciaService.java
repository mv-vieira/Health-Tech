package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.infra.repository.AmbulanciaRepository;
import com.br.healthtech.infra.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmbulanciaService {

    @Autowired
    AmbulanciaRepository ambulanciaRepository;


    // Find All
    public Page<Ambulancia> findAll(Pageable page) {
        return ambulanciaRepository.findAll(page);
    }

    // Find by ID
    @Transactional
    public Optional<Ambulancia> findById(int id) {
        Optional<Ambulancia> ambulanciaOptional = ambulanciaRepository.findById(id);

        if (ambulanciaOptional.isPresent()) {
            Ambulancia ambulancia = ambulanciaOptional.get();
//            ambulancia.getPacientes().size();
            return Optional.of(ambulancia);
        } else {
            throw new EntityNotFoundException("Ambulância não encontrada");
        }
    }

    // Find by Placa
    @Transactional
    public Ambulancia findByPlaca(String placa) {
        return ambulanciaRepository.findByPlacaAmbulancia(placa);
    }

    // Cadastrar ambulância
    public void saveAmbulancia(Ambulancia ambulancia) {
        ambulanciaRepository.save(ambulancia);
    }

}

