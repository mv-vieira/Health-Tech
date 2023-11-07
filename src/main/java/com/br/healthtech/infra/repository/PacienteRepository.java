package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCpf (String cpf);

    Optional<Paciente> findByCpf (String cpf);

}
