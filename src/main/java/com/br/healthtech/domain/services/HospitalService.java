package com.br.healthtech.domain.services;

import com.br.healthtech.domain.entity.Hospital;
import com.br.healthtech.infra.repository.HospitalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    // Find by Nome
    public Optional<Hospital> findByNome(String nome) throws Exception{
        Optional<Hospital> hospital = hospitalRepository.findByNomeHospital(nome);

        if (hospital == null){
            throw new Exception("Hospital com nome: " + nome + " não foi encontrado");
        }
        return hospital;
    }

    // Find By Municipio
    public List<Hospital> findByMunicipio(String municipio) throws Exception{
        List<Hospital> hospitalList = hospitalRepository.findByMunicipio(municipio);

        if (hospitalList.isEmpty()){
            throw new Exception("Não foi encontrado nenhum hospital no município: " + municipio);
        }
        return hospitalList;
    }

    // Find by ID
    @Transactional
    public Optional<Hospital> findById(int id) throws Exception {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        if (hospitalOptional.isPresent()) {
            Hospital hospital = hospitalOptional.get();
            return Optional.of(hospital);
        } else {
            throw new EntityNotFoundException("Hospital não encontrado");
        }
    }

    //Find All Hospitais
    public Page<Hospital> findAll(Pageable page){
        return hospitalRepository.findAll(page);
    }
}
