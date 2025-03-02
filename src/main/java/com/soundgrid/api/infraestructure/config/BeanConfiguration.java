package com.soundgrid.api.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soundgrid.api.application.port.input.MusicUseCase;
import com.soundgrid.api.application.port.output.MusicRepository;
import com.soundgrid.api.application.service.MusicServiceImpl;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public MusicUseCase musicUseCase(MusicRepository repository) {
        return new MusicServiceImpl(repository);
    }
}
