package com.br.healthtech.web.dto;

import com.br.healthtech.domain.entity.enuns.AmbulanciaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AmbulanciaDto(AmbulanciaType tipoAmbulancia,
                            @NotBlank
                            @NotNull
                            @Size(max = 8)
                            String placaAmbulancia) {

    public AmbulanciaDto(AmbulanciaType tipoAmbulancia,
                         @NotBlank
                         @NotNull
                         @Size(max = 8)
                         String placaAmbulancia) {
        this.tipoAmbulancia = tipoAmbulancia;
        this.placaAmbulancia = placaAmbulancia;
    }
}

