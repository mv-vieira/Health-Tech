package com.br.healthtech.repositories;

import com.br.healthtech.models.AmbulanciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbulanciaRepository extends JpaRepository<AmbulanciaModel, Integer> {
}
