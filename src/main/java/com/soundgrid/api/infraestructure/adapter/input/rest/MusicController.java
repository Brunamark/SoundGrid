package com.soundgrid.api.infraestructure.adapter.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soundgrid.api.application.port.input.MusicUseCase;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.infraestructure.adapter.input.rest.request.MusicRequest;
import com.soundgrid.api.infraestructure.adapter.input.rest.response.MusicResponse;
import com.soundgrid.api.infraestructure.adapter.output.peristance.mapper.MusicMapper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/music")
public class MusicController {
    private final MusicUseCase musicUseCase;
    private final MusicMapper mapper;

    public MusicController(MusicUseCase musicUseCase, MusicMapper mapper) {
        this.musicUseCase = musicUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<MusicResponse> createMusic(@Valid @RequestBody MusicRequest request) {
        Music music = mapper.toDomain(request);
        Music created = musicUseCase.createMusic(music);
        return new ResponseEntity<>(mapper.toResponse(created), HttpStatus.CREATED);
    }

    
    

    

}
