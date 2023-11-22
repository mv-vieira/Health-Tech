package com.br.healthtech.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "tb_paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_paciente")
    private int id;

    @Column (name = "nome_paciente", nullable = false)
    private String nome;

    @Column (name= "data_nascimento_paciente", nullable = false)
    private LocalDate dataNascimento;

    @Column (name = "cpf_paciente", nullable = false, unique = true)
    private String cpf;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_id_ambulancia")
    @JsonIgnore
    private Ambulancia ambulancia;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonBackReference
    private Ocorrencia ocorrencia;

}
