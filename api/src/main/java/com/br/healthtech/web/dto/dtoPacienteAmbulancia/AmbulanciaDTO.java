package com.br.healthtech.web.dto.dtoPacienteAmbulancia;

import com.br.healthtech.domain.entity.Ambulancia;
import com.br.healthtech.domain.entity.enuns.AmbulanciaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmbulanciaDTO {

    private int id;
    private AmbulanciaType tipoAmbulancia;
    private String placaAmbulancia;
    // Outros campos relevantes da Ambulancia

    public AmbulanciaDTO(Ambulancia ambulancia) {
        this.id = ambulancia.getId();
        this.tipoAmbulancia = ambulancia.getTipoAmbulancia();
        this.placaAmbulancia = ambulancia.getPlacaAmbulancia();
        // Inclua outros campos relevantes da Ambulancia, se necessário
    }
}
