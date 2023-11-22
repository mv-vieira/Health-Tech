package com.br.healthtech.domain.entity;

import com.br.healthtech.domain.entity.enuns.UtiType;
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
    @Column (name = "id_hospital")
    private Integer id;

    @Column(name = "nome_hospital")
    private String nomeHospital;

    private String cnpj;

    @Column(name = "vaga_uti")
    @Enumerated(EnumType.STRING)
    private UtiType disponibilidade;

    private String especialidade;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ocorrencia> ocorrencias;

}
