package com.br.healthtech.infra.config;

import com.br.healthtech.domain.services.utils.ProtocoloGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProtocoloConfig {

    @Bean
    public ProtocoloGenerator numeroAutomaticoGenerator() {
        return new ProtocoloGenerator();
    }
}


