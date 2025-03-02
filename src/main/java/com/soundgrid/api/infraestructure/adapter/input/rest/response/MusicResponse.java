package com.soundgrid.api.infraestructure.adapter.input.rest.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.soundgrid.api.domain.types.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicResponse {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private Genre genre;
    private LocalDate releaseDate;
    private Integer duration;
    private String filePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
