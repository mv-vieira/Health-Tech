package com.br.healthtech.repositories;

import com.br.healthtech.models.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Integer> {

    boolean existsByCpf (String cpf);

    Optional<PacienteModel> findByCpf (String cpf);

}
