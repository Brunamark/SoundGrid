package com.soundgrid.api.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.soundgrid.api.application.port.output.MusicRepository;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;
import com.soundgrid.api.infraestructure.adapter.output.peristence.entity.MusicEntity;
import com.soundgrid.api.infraestructure.adapter.output.peristence.mapper.MusicMapper;
import com.soundgrid.api.infraestructure.adapter.output.peristence.repository.SpringDataMusicRepository;

@Service
public class MusicRepositoryImpl implements MusicRepository {

    private final SpringDataMusicRepository springDataMusicRepository;
    private final MusicMapper musicMapper;

    public MusicRepositoryImpl(SpringDataMusicRepository springDataMusicRepository, MusicMapper musicMapper) {
        this.springDataMusicRepository = springDataMusicRepository;
        this.musicMapper = musicMapper;
    }

    @Override
    public Music save(Music music) {
        if (!StringUtils.hasText(music.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (!StringUtils.hasText(music.getArtist())) {
            throw new IllegalArgumentException("Artist cannot be empty");
        }

        if (!StringUtils.hasText(music.getFilePath())) {
            throw new IllegalArgumentException("File path cannot be empty");
        }
        MusicEntity entity = musicMapper.toEntity(music);
        System.out.println("entity: "+ entity.getAlbum());
        return musicMapper.toDomain(springDataMusicRepository.save(entity));
    }

    @Override
    public Optional<Music> findById(Long id) {
        return springDataMusicRepository.findById(id).map(musicMapper::toDomain);
    }

    @Override
    public Map<Genre, List<Music>> findAll() {
        List<Music> musicList = springDataMusicRepository.findAll()
                .stream()
                .map(musicMapper::toDomain)
                .collect(Collectors.toList());

        return musicList.stream().collect(Collectors.groupingBy(Music::getGenre));
    }

    @Override
    public Map<Genre, List<Music>> findByTitleContainingOrArtistContaining(String title, String artist) {
        List<Music> musicList = springDataMusicRepository
                .findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(title, artist)
                .stream()
                .map(musicMapper::toDomain)
                .collect(Collectors.toList());

        return musicList.stream().collect(Collectors.groupingBy(Music::getGenre));
    }

    @Override
    public void deleteById(Long id) {
        springDataMusicRepository.deleteById(id);
    }

    @Override
    public boolean isIdExists(Long id) {
        return springDataMusicRepository.existsById(id);
    }
}
