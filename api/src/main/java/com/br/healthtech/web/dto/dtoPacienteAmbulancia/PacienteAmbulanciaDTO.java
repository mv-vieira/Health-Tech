package com.br.healthtech.web.dto.dtoPacienteAmbulancia;

import com.br.healthtech.domain.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteAmbulanciaDTO {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    // Outros campos relevantes do Paciente, exceto ambulancia e ocorrencia

    private AmbulanciaDTO ambulancia;

    public PacienteAmbulanciaDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.dataNascimento = paciente.getDataNascimento();
        this.cpf = paciente.getCpf();
        // Inclua outros campos relevantes do Paciente, se necessário

        if (paciente.getAmbulancia() != null) {
            this.ambulancia = new AmbulanciaDTO(paciente.getAmbulancia());
        }
    }
}
