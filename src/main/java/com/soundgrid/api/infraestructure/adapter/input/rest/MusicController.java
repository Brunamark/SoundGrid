package com.soundgrid.api.infraestructure.adapter.input.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soundgrid.api.application.port.input.MusicUseCase;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;
import com.soundgrid.api.infraestructure.adapter.input.rest.request.MusicRequest;
import com.soundgrid.api.infraestructure.adapter.input.rest.response.MusicResponse;
import com.soundgrid.api.infraestructure.adapter.output.peristence.mapper.MusicMapper;

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

    @GetMapping("/(id)")
    public ResponseEntity<MusicResponse> getMusicById(@PathVariable Long id) {
        Music music = musicUseCase.getMusicById(id);
        return ResponseEntity.ok(mapper.toResponse(music));
    }

    @GetMapping
    public ResponseEntity<List<MusicResponse>> getAllMusic() {
        Map<Genre, List<Music>> musicMap = musicUseCase.getAllMusic();
        List<MusicResponse> responses = musicMap.values().stream()
                .flatMap(List::stream)
                .map(music -> {
                    MusicResponse response = new MusicResponse();
                    response.setId(music.getId());
                    response.setTitle(music.getTitle());
                    response.setArtist(music.getArtist());
                    response.setAlbum(music.getAlbum());
                    response.setGenre(music.getGenre());
                    response.setReleaseDate(music.getReleaseDate());
                    response.setDuration(music.getDuration());
                    response.setFilePath(music.getFilePath());
                    response.setCreatedAt(music.getCreatedAt());
                    response.setUpdatedAt(music.getUpdatedAt());
                    return response;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicResponse> updateMusic(@PathVariable Long id, @RequestBody MusicRequest request) {
        Music music = mapper.toDomain(request);
        Music updated = musicUseCase.updateMusic(id, music);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicUseCase.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }

   

}
