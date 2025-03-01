package com.soundgrid.api.application.port.output;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.soundgrid.api.domain.model.Music;

public interface MusicRepository {
    Music save(Music music);
    Optional<Music> findById(Long id);
    Map<String, List<Music>> findAll();
    Map<String, List<Music>> findByTitleContainingOrArtistContaining(String title, String artist);
    void deleteById(Long id);
    boolean isIdExists(Long id);    
}
