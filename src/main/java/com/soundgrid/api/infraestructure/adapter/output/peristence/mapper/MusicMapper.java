package com.soundgrid.api.infraestructure.adapter.output.peristence.mapper;

import org.springframework.stereotype.Component;

import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.infraestructure.adapter.input.rest.request.MusicRequest;
import com.soundgrid.api.infraestructure.adapter.input.rest.response.MusicResponse;
import com.soundgrid.api.infraestructure.adapter.output.peristence.entity.MusicEntity;

@Component
public class MusicMapper {

    public Music toDomain(MusicRequest request) {
        return new Music(
                null,
                request.getTitle(),
                request.getArtist(),
                request.getAlbum(),
                request.getGenre(),
                request.getReleaseDate(),
                request.getDuration(),
                request.getFilePath(),
                null,
                request.getUpdatedAt());
    }

    public MusicResponse toResponse(Music music) {
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
    }

    public MusicEntity toEntity(Music music) {
        return new MusicEntity(
                music.getId(),
                music.getTitle(),
                music.getArtist(),
                music.getAlbum(),
                music.getGenre(),
                music.getReleaseDate(),
                music.getDuration(),
                music.getFilePath(),
                music.getCreatedAt() != null ? music.getCreatedAt() : java.time.LocalDateTime.now(), 
                music.getUpdatedAt());
    }

    public Music toDomain(MusicEntity entity) {
        return new Music(
                entity.getId(),
                entity.getTitle(),
                entity.getArtist(),
                entity.getAlbum(),
                entity.getGenre(),
                entity.getReleaseDate(),
                entity.getDuration(),
                entity.getFilePath(),
                 entity.getCreatedAt(), 
                entity.getUpdatedAt());
    }
}
