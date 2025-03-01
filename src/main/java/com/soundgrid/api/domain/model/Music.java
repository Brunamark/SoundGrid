package com.soundgrid.api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.soundgrid.api.domain.types.Genre;

import jakarta.validation.constraints.NotNull;

public class Music {
    @NotNull(message = "The id cannot be null.")
    private Long id;
    @NotNull(message = "The title cannot be null.")
    private String title;
    @NotNull(message = "The artist cannot be null.")
    private String artist;
    private String album;
    @NotNull(message = "The genre cannot be null.")
    private Genre genre;
    @NotNull(message = "The release date cannot be null.")
    private LocalDate releaseDate;
    private int duration;
    @NotNull(message = "The file path cannot be null.")
    private String filePath;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Music(@NotNull(message = "The id cannot be null.") Long id,
            @NotNull(message = "The title cannot be null.") String title,
            @NotNull(message = "The artist cannot be null.") String artist, String album,
            @NotNull(message = "The genre cannot be null.") Genre genre,
            @NotNull(message = "The release date cannot be null.") LocalDate releaseDate, int duration,
            @NotNull(message = "The file path cannot be null.") String filePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.filePath = filePath;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
