package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Ambulancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Integer> {

    Ambulancia findByPlacaAmbulancia(String placaAmbulancia);
}
