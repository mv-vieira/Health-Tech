package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {


    Optional<Ocorrencia> findByProtocolo(String protocolo);
}
