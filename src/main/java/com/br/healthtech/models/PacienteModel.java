package com.br.healthtech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_paciente")
@Getter
@Setter
public class PacienteModel {

    @Id
    @Column (name = "id_paciente")
    private int id;

    @Column (name = "nome_paciente", nullable = false)
    private String nome;

    @Column (name= "data_nascimento_paciente", nullable = false)
    private LocalDate dataNascimento;

    @Column (name = "cpf_paciente", nullable = false)
    private String cpf;

}
