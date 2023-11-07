package com.br.healthtech.web.dto;

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
        public PacienteDto(@NotBlank
                           String nome, LocalDate dataNascimento, @NotBlank
                           @Size(max = 14)
                           String cpf) {
                this.nome = nome;
                this.dataNascimento = dataNascimento;
                this.cpf = cpf;
        }
}
