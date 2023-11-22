package com.br.healthtech.infra.repository;

import com.br.healthtech.domain.entity.Hospital;
import com.br.healthtech.domain.entity.enuns.UtiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    Optional<Hospital> findByNomeHospital(String nome);

    @Query("SELECT h FROM Hospital h WHERE LOWER(h.endereco) LIKE %:nomeDigitado%")
    List<Hospital> findByEnderecoContainingNomeDigitado(@Param("nomeDigitado") String nomeDigitado);

    Optional<Hospital> findByDisponibilidade(UtiType disponibilidade);
}
