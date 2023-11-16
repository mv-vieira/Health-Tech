package com.br.healthtech.web.dto;

import com.br.healthtech.domain.entity.enuns.AmbulanciaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AmbulanciaDto(AmbulanciaType tipoAmbulancia,
                            @NotBlank  String placaAmbulancia) {

    public AmbulanciaDto(AmbulanciaType tipoAmbulancia,
                         @NotBlank String placaAmbulancia) {
        this.tipoAmbulancia = tipoAmbulancia;
        this.placaAmbulancia = placaAmbulancia;
    }
}

