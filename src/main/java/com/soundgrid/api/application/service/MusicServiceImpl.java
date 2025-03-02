package com.soundgrid.api.application.service;

import java.util.List;
import java.util.Map;

import com.soundgrid.api.application.port.input.MusicUseCase;
import com.soundgrid.api.application.port.output.MusicRepository;
import com.soundgrid.api.domain.exception.MusicNotFoundException;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;

public class MusicServiceImpl implements MusicUseCase {

    private final MusicRepository musicRepository;

    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Music createMusic(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public Music getMusicById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("Music not found with id: " + id));
    }

    @Override
    public Map<Genre, List<Music>> getAllMusic() {
        return musicRepository.findAll();
    }

    @Override
    public Map<Genre, List<Music>> searchMusic(String keyword) {
        return musicRepository.findByTitleContainingOrArtistContaining(keyword, keyword);
    }

    @Override
    public Music updateMusic(Long id, Music music) {
        if (!musicRepository.isIdExists(id)) {
            throw new MusicNotFoundException("Music not found with id: " + id);
        }
        music.setId(id);

        return musicRepository.save(music);
    }

    @Override
    public void deleteMusic(Long id) {
        if (!musicRepository.isIdExists(id)) {
            throw new MusicNotFoundException("Music not found with id: " + id);
        }
        musicRepository.deleteById(id);
    }

}
