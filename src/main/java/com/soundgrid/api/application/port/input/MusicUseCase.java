package com.soundgrid.api.application.port.input;


import java.util.List;
import java.util.Map;

import com.soundgrid.api.domain.model.Music;

public interface MusicUseCase {
    Music createMusic(Music music);
    Music getMusicById(Long id);
    Map<String, List<Music>> getAllMusic();
    Map<String, List<Music>> searchMusic(String keyword);
    Music updateMusic(Long id, Music music); 
    void deleteMusic(Long id);
}
