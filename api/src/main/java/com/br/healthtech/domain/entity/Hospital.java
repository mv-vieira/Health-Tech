package com.br.healthtech.domain.entity;

import com.br.healthtech.domain.entity.enuns.UtiType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_hospitais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_hospital", nullable = false)
    private Integer id;

    @Column(name = "nome_hospital", nullable = false)
    private String nomeHospital;

    private String cnpj;

    @Column(name = "vaga_uti", nullable = false)
    @Enumerated(EnumType.STRING)
    private UtiType disponibilidade;

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String municipio;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties("hospital")
    private List<Ocorrencia> ocorrencias;

}
