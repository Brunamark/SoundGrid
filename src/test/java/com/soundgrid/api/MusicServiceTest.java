package com.soundgrid.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soundgrid.api.application.port.output.MusicRepository;
import com.soundgrid.api.application.service.MusicServiceImpl;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;

@ExtendWith(MockitoExtension.class)
class MusicServiceTests {

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private MusicServiceImpl musicService;

    private Music testMusic;

    @BeforeEach
    void setUp() {
        testMusic = new Music(
                1L,
                "Bohemian Rhapsody",
                "Queen",
                "A Night at the Opera",
                Genre.ROCK,
                LocalDate.of(1975, 11, 21),
                354,
                "/music/queen/bohemian_rhapsody.mp3",
                null,
                null);
    }

    @Test
    void testCreateMusic() {
        when(musicRepository.save(any(Music.class))).thenReturn(testMusic);
        Music createdMusic = musicService.createMusic(testMusic);
        assertNotNull(createdMusic);
        assertEquals("Bohemian Rhapsody", createdMusic.getTitle());
        verify(musicRepository, times(1)).save(any(Music.class));
    }

    @Test
    void testGetMusicById() {
        when(musicRepository.findById(1L)).thenReturn(Optional.of(testMusic));
        Music foundMusic = musicService.getMusicById(1L);
        assertNotNull(foundMusic);
        assertEquals("Bohemian Rhapsody", foundMusic.getTitle());
    }

    @Test
    void testGetAllMusic() {
        when(musicRepository.findAll()).thenReturn(Map.of(Genre.ROCK, List.of(testMusic)));
        var musicList = musicService.getAllMusic();
        assertFalse(musicList.isEmpty());
        assertTrue(musicList.containsKey(Genre.ROCK));
    }

    @Test
    void testDeleteMusic() {
        when(musicRepository.isIdExists(1L)).thenReturn(true);
        doNothing().when(musicRepository).deleteById(1L);
        musicService.deleteMusic(1L);
        verify(musicRepository, times(1)).deleteById(1L);
    }
}
