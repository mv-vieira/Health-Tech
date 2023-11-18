package com.br.healthtech.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ocorrencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {

    @Id
    @Column(name = "id_ocorrencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_protocolo")
    private Integer protocolo;

    @Column(name = "endereco_ocorrencia")
    private String endereco;

    @Column(name = "hora_ocorrencia")
    private LocalDateTime horaOcorrencia;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_id_ambulancia")
    @JsonIgnore
    private Ambulancia ambulancia;
}
