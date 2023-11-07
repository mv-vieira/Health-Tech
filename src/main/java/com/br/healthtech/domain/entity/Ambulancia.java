package com.br.healthtech.domain.entity;

import com.br.healthtech.domain.entity.enuns.AmbulanciaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_ambulancia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ambulancia {

    @Id
    @Column(name = "id_ambulancia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AmbulanciaType tipo_ambulancia;

    @Column(unique = true)
    private String placa_ambulancia;

    @OneToMany(mappedBy = "ambulancia")
    private List<Paciente> pacientes;


    public Ambulancia(Ambulancia ambulancia) {
    }
}
