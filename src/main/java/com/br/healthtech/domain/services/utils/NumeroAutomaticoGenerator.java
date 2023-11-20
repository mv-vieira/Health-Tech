package com.br.healthtech.domain.services.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class NumeroAutomaticoGenerator {

    public String gerarNumeroAutomatico() {
        int anoAtual = LocalDate.now().getYear();
        long numeroAleatorio = gerarNumeroAleatorio();
        return formatarNumero(anoAtual, numeroAleatorio);
    }

    private long gerarNumeroAleatorio() {
        // Gera um número aleatório entre 10000000 e 99999999
        return ThreadLocalRandom.current().nextLong(90000000) + 10000000;
    }

    private String formatarNumero(int ano, long numeroAleatorio) {
        // Formata o número para garantir que sempre tenha 12 dígitos
        DecimalFormat formato = new DecimalFormat("00000000");
        return String.format("%04d%s", ano, formato.format(numeroAleatorio));
    }
}
