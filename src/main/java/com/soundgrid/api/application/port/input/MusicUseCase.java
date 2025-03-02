package com.soundgrid.api.application.port.input;


import java.util.List;
import java.util.Map;

import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;

public interface MusicUseCase {
    Music createMusic(Music music);
    Music getMusicById(Long id);
    Map<Genre, List<Music>> getAllMusic();
    Map<Genre, List<Music>> searchMusic(String keyword);
    Music updateMusic(Long id, Music music); 
    void deleteMusic(Long id);
}
