package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Ambulancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Integer> {
}
