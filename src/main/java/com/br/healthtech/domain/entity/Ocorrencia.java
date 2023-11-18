package com.br.healthtech.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

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

    @Column(name = "numero_protocolo", nullable = false)
    private Integer protocolo;

    @Column(name = "endereco_ocorrencia",nullable = false)
    private String endereco;

    @Column(name = "hora_ocorrencia",nullable = false)
    private LocalDateTime horaOcorrencia;

    @Lob
    private String descricao;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_id_ambulancia")
    @JsonIgnore
    private Ambulancia ambulancia;
}
