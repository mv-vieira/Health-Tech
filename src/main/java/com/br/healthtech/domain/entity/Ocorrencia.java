package com.br.healthtech.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "numero_protocolo", nullable = false, length = 12)
    private String protocolo;

    @Column(name = "endereco_ocorrencia",nullable = false)
    private String endereco;

    @Column(name = "horario_ocorrencia")
    private LocalDateTime dataHora;

    @Lob
    private String descricao;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_id_ambulancia")
    @JsonIgnoreProperties("ocorrencias")
    private Ambulancia ambulancia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "fk_id_paciente")
    @JsonIgnoreProperties("ocorrencias")
    private Paciente paciente;
}
