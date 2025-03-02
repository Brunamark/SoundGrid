package com.soundgrid.api.infraestructure.adapter.input.rest.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.soundgrid.api.domain.types.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicRequest {
    private Long id;
    @NotBlank(message = "The title cannot be null.")
    private String title;
    @NotBlank(message = "The artist cannot be null.")
    private String artist;
    private String album;
    @NotNull(message = "The genre cannot be null.")
    private Genre genre;
    @NotNull(message = "The release date cannot be null.")
    private LocalDate releaseDate;
    private int duration;
    @NotNull(message = "The file path cannot be null.")
    private String filePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}