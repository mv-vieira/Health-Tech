package com.br.healthtech.models;

import com.br.healthtech.enuns.AmbulanciaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_ambulancia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmbulanciaModel {

    @Id
    @Column(name = "id_ambulancia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AmbulanciaType tipo_ambulancia;

    @Column(unique = true)
    private String placa_ambulancia;

    @OneToMany(mappedBy = "ambulancia")
    private List<PacienteModel> pacientes;


    public AmbulanciaModel(AmbulanciaModel ambulanciaModel) {
    }
}
