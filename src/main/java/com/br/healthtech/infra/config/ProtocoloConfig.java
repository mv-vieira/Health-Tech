package com.br.healthtech.infra.config;

import com.br.healthtech.domain.services.utils.NumeroAutomaticoGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class ProtocoloConfig {

    @Bean
    public NumeroAutomaticoGenerator numeroAutomaticoGenerator() {
        return new NumeroAutomaticoGenerator();
    }
}


