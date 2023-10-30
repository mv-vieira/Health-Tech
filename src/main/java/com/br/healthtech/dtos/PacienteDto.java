package com.br.healthtech.dtos;

import com.br.healthtech.models.PacienteModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PacienteDto(
        @NotBlank
        String nome,

        LocalDate dataNascimento,

        @NotBlank
        @Size(max = 14)
        String cpf
) {

}
