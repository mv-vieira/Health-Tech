package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {


    Ocorrencia findByProtocolo(Integer protocolo);
}
