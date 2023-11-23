package com.br.healthtech.web.dto;

import com.br.healthtech.domain.entity.Ambulancia;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OcorrenciaDto(@NotBlank @NotNull String endereco) {

    public OcorrenciaDto(@NotBlank @NotNull String endereco) {
        this.endereco = endereco;
    }
}
