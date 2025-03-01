package com.soundgrid.api.infraestructure.adapter.output.peristance.mapper;

import org.springframework.stereotype.Component;

import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.infraestructure.adapter.input.rest.request.MusicRequest;
import com.soundgrid.api.infraestructure.adapter.input.rest.response.MusicResponse;
import com.soundgrid.api.infraestructure.adapter.output.peristance.entity.MusicEntity;

@Component
public class MusicMapper {

    public Music toDomain(MusicRequest request) {
        Music music = new Music();
        music.setTitle(request.getTitle());
        music.setArtist(request.getArtist());
        music.setAlbum(request.getAlbum());
        music.setGenre(request.getGenre());
        music.setReleaseDate(request.getReleaseDate());
        music.setDuration(request.getDuration());
        music.setFilePath(request.getFilePath());
        return music;
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
        MusicEntity entity = new MusicEntity();
        entity.setId(music.getId());  
        entity.setTitle(music.getTitle());
        entity.setArtist(music.getArtist());
        entity.setAlbum(music.getAlbum());
        entity.setGenre(music.getGenre());  
        entity.setReleaseDate(music.getReleaseDate());
        entity.setDuration(music.getDuration());
        entity.setFilePath(music.getFilePath());
        entity.setCreatedAt(music.getCreatedAt());
        entity.setUpdatedAt(music.getUpdatedAt());
        return entity;
    }

    public Music toDomain(MusicEntity entity) {
        Music music = new Music();
        music.setId(entity.getId());
        music.setTitle(entity.getTitle());
        music.setArtist(entity.getArtist());
        music.setAlbum(entity.getAlbum());
        music.setGenre(entity.getGenre());  
        music.setReleaseDate(entity.getReleaseDate());
        music.setDuration(entity.getDuration());
        music.setFilePath(entity.getFilePath());
        music.setUpdatedAt(entity.getUpdatedAt());
        return music;
    }
}
